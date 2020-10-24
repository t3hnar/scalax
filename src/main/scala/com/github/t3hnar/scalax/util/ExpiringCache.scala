package com.github.t3hnar.scalax.util

import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

import scala.collection.concurrent.TrieMap
import scala.concurrent.duration._
import scala.concurrent.{ ExecutionContext, Future }

/**
 * @author Yaroslav Klymko, Sergiy Prydatchenko
 */
class ExpiringCache[K, V](
  val duration:      Long,
  val unit:          TimeUnit,
  val queryOverflow: Int              = 1000,
  executionContext:  ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global) {

  implicit val ec = executionContext

  def this(duration: FiniteDuration, queryOverflow: Int)(implicit ec: ExecutionContext) =
    this(duration.length, duration.unit, queryOverflow, ec)

  case class ExpiringValue(value: V, timestamp: Long)

  private[util] val map: TrieMap[K, ExpiringValue] = TrieMap.empty

  private val lock = new ReentrantLock()

  def get(key: K): Option[V] = {
    if (queryCount == queryOverflow) maybeCleanExpired() else increaseQueryCount()
    map.get(key).collect {
      case ExpiringValue(value, timestamp) if !isExpired(timestamp) => value
    }
  }

  def put(entry: K, value: V): Option[V] = {
    val res = map.put(entry, ExpiringValue(value, currentMillis))
    res map (_.value)
  }

  def remove(entry: K): Option[V] = map.remove(entry) map (_.value)

  protected def currentMillis = System.currentTimeMillis()

  @volatile private[util] var queryCount = 0

  protected def increaseQueryCount(): Unit = {
    try {
      lock.lock()
      queryCount = queryCount + 1
    } finally {
      if (lock.isHeldByCurrentThread) lock.unlock()
    }
  }

  protected def maybeCleanExpired(): Unit = {
    try {
      lock.lock()
      queryCount = 0
      cleanExpired()
    } finally {
      if (lock.isHeldByCurrentThread) lock.unlock()
    }
  }

  protected lazy val durationMillis = unit.toMillis(duration)
  protected def isExpired(timestamp: Long) =
    (timestamp + durationMillis) <= currentMillis

  def cleanExpired(): Unit = {
    Future {
      val expired = map.toList.collect {
        case (key, ExpiringValue(_, timestamp)) if isExpired(timestamp) => key
      }
      expired.foreach(map.remove)
    }
    ()
  }
}