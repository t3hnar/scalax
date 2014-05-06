package com.github.t3hnar.scalax.util

import java.util.concurrent.TimeUnit
import collection.mutable

/**
 * @author Yaroslav Klymko
 */
class ExpiringCache[K, V](
    val duration: Long,
    val unit: TimeUnit,
    val queryOverflow: Int = 1000) {

  case class ExpiringValue(value: V, timestamp: Long)

  val map = new mutable.HashMap[K, ExpiringValue]

  def get(key: K): Option[V] = {
    increaseQueryCount()
    maybeCleanExpired()
    map.get(key).collect {
      case ExpiringValue(value, timestamp) if !isExpired(timestamp) => value
    }
  }

  def put(entry: K, value: V): Option[V] =
    map.put(entry, ExpiringValue(value, currentMillis)).map(_.value)

  protected def currentMillis = System.currentTimeMillis()

  private[util] var queryCount = 0

  protected def increaseQueryCount() {
    queryCount = queryCount + 1
  }

  protected def maybeCleanExpired() {
    if (queryCount >= queryOverflow) cleanExpired()
  }

  protected lazy val durationMillis = unit.toMillis(duration)
  protected def isExpired(timestamp: Long) =
    (timestamp + durationMillis) <= currentMillis

  def cleanExpired() {
    val expired = map.toList.collect {
      case (key, ExpiringValue(_, timestamp)) if isExpired(timestamp) => key
    }
    expired.foreach(map.remove)
  }
}

class SynchronizedExpiringCache[K, V](duration: Long,
  unit: TimeUnit,
  queryOverflow: Int = 1000)
    extends ExpiringCache[K, V](duration, unit, queryOverflow) {

  override def get(key: K): Option[V] = synchronized {
    super.get(key)
  }

  override def put(key: K, value: V) = synchronized {
    super.put(key, value)
  }

  override def cleanExpired(): Unit = synchronized {
    super.cleanExpired()
  }
}
