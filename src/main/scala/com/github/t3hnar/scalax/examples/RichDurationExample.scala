package com.github.t3hnar.scalax
package examples

/**
 * @author Yaroslav Klymko
 */
object RichDurationExample {

  import concurrent.duration._

  60.seconds.toCoarsest // 1.minute
  60.minutes.toCoarsest // 1.hour
  180.minutes.toCoarsest // 3.hours
  24.hours.toCoarsest // 1.day
  Duration.Inf.toCoarsest // Duration.Inf
  Duration.MinusInf.toCoarsest // Duration.MinusInf
}
