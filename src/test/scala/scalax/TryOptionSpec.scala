package scalax

import org.specs2.mutable.SpecificationWithJUnit

/**
 * @author Yaroslav Klymko
 */
class TryOptionSpec extends SpecificationWithJUnit {
  "TryOption" should {
    "return None if exception occurred" in {
      TryOption(sys.error("Error")) must beNone
    }

    "return Some if no exception occurred" in {
      TryOption("Not an error") mustEqual Some("Not an error")
    }

    "not catch errors" in {
      TryOption(assert(false)) must throwA[AssertionError]
    }
  }
}
