package scalax.util

import org.specs2.mutable.SpecificationWithJUnit
import java.util.concurrent.TimeUnit
import org.specs2.specification.Scope

/**
 * @author Yaroslav Klymko
 */
class ExpiringCacheSpec extends SpecificationWithJUnit {
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

      cache.map must haveSize(1)
      cache.get(1) must beSome("1")
    }

    "not return expired values which are not cleaned" in new ExpiringCacheScope {
      cache.map must haveSize(0)
      cache.queryCount mustEqual 0

      cache.put(0, "0")
      cache.get(0) must beSome("0")
      cache.map must haveSize(1)

      current = cache.unit.toMillis(cache.duration)

      cache.get(0) must beNone
      cache.map must haveSize(1)
    }

    class ExpiringCacheScope extends Scope {
      var current = 0L
      val cache = new ExpiringCache[Int, String](1, TimeUnit.MILLISECONDS, 5) {
        override def currentMillis = current
      }
    }
  }
}

