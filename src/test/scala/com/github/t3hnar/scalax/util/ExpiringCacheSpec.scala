package com.github.t3hnar.scalax.util

import java.util.concurrent.{ Executors, TimeUnit }

import com.github.t3hnar.scalax.util
import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.concurrent.duration.{ DurationInt, FiniteDuration }
import scala.util.Random

/**
 * @author Yaroslav Klymko
 */
class ExpiringCacheSpec extends Specification {
  "ExpiringCache" should {
    "clean expired values if get enough queries" in new ExpiringCacheScope {
      cache.map must haveSize(0)
      cache.queryCount mustEqual 0

      cache.put(0, "0")
      cache.get(0) must beSome("0")
      cache.map must haveSize(1)
      cache.queryCount mustEqual 1

      current = cache.unit.toMillis(cache.duration)

      cache.put(1, "1")
      cache.get(1) must beSome("1")
      cache.queryCount mustEqual 2

      (0 to cache.queryOverflow).foreach(_ => cache.get(3))

      cache.map.size must eventually(beEqualTo(1))
      cache.get(1) must beSome("1")
    }

    "not return expired values which are not cleaned" in new ExpiringCacheScope {
      cache.map must haveSize(0)
      cache.queryCount mustEqual 0

      cache.put(0, "0")
      cache.get(0) must beSome("0")
      cache.map.size must eventually(beEqualTo(1))

      current = cache.unit.toMillis(cache.duration)

      cache.get(0) must beNone
      cache.map.size must eventually(beEqualTo(1))
    }

    "not contain value after it was removed" in new ExpiringCacheDefaultScope {
      cache.map must haveSize(0)
      cache.put(0, "0")
      cache.map must haveSize(1)
      cache.remove(0)
      cache.get(0) must beNone
    }

    "after 'put' operation returns value previously associated with key" in new ExpiringCacheDefaultScope {
      cache.map must haveSize(0)
      cache.put(0, "0")
      val result: Option[String] = cache.put(0, "1")
      result must beSome("0")
    }

    "provides stable cleaning in multi-thread environment" in new ExpiringCacheAsyncScope {
      cache.map must haveSize(0)
      cache.queryCount shouldEqual 0
      cache.put(0, "0")

      val futures = List.fill(6)(Future {
        cache.get(0)
      }(ExecutionContext.fromExecutor(Executors.newSingleThreadExecutor())))

      implicit val resultExecutionContext = ExecutionContext.global
      val result = Future.sequence(futures)
      Await.ready(result, 1.second)
      cache.queryCount shouldEqual 1
    }
  }

  class ExpiringCacheScope extends Scope {
    var current = 0L
    val cache = new ExpiringCache[Int, String](1, TimeUnit.MILLISECONDS, 5) {
      override def currentMillis = current
    }
  }

  class ExpiringCacheDefaultScope extends Scope {
    implicit val executionContext = ExecutionContext.global
    val cache = new ExpiringCache[Int, String](new FiniteDuration(1, TimeUnit.MILLISECONDS), 5)
  }

  class ExpiringCacheAsyncScope extends Scope {
    implicit val executionContext = ExecutionContext.global
    val cache = new ExpiringCache[Int, String](new FiniteDuration(500, TimeUnit.MILLISECONDS), 4)
  }
}