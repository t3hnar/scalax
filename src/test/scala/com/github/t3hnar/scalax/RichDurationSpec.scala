package com.github.t3hnar.scalax

import org.specs2.mutable.Specification
import scala.concurrent.duration._

/**
 * @author Yaroslav Klymko
 */
class RichDurationSpec extends Specification {
  "RichDuration.toCoarsest" should {
    "convert infinite to infinite" in {
      RichDuration(Duration.Inf).toCoarsest mustEqual Duration.Inf
      RichDuration(Duration.MinusInf).toCoarsest mustEqual Duration.MinusInf
    }

    "convert finite durations" in forall(List(
      (60.minutes, 1.hour),
      (2000.millis, 2.seconds),
      (2000.micros, 2.millis),
      (2000.nanos, 2.micros),
      (2000000.nanos, 2.millis),
      (48.hours, 2.days),
      (5.seconds, 5.seconds),
      (0.seconds, 0.seconds),
      (0.days, 0.days),
      (1.days, 1.days))) {
      case (x, expected) =>
        val actual = RichDuration(x).toCoarsest
        actual.unit mustEqual expected.unit
        actual.length mustEqual expected.length
    }
  }
}
