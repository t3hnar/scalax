package scalax

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.time.NoTimeConversions
import scala.concurrent.duration._


/**
 * @author Yaroslav Klymko
 */
class RichDurationSpec extends SpecificationWithJUnit with NoTimeConversions {
  "RichDuration.toCoarsest" should {
    "convert infinite to infinite" in {
      Duration.Inf.toCoarsest mustEqual Duration.Inf
      Duration.MinusInf.toCoarsest mustEqual Duration.MinusInf
    }
    "convert 60 seconds to 1 minute" in {
      60.seconds.toCoarsest mustEqual 1.minute
    }
    "convert 60 minutes to 1 hour" in {
      60.minutes.toCoarsest mustEqual 1.hour
    }
    "convert 180 minutes to 3 hours" in {
      180.minutes.toCoarsest mustEqual 3.hours
    }
    "convert 24 hours to 1 day" in {
      24.hours.toCoarsest mustEqual 1.day
    }
  }
}
