package scalax

import org.specs2.mutable.SpecificationWithJUnit

class RichStringSpec extends SpecificationWithJUnit {

  "RichString.toIntOpt" should {

    "return Some for valid integer strings" in {
      "42".toIntOpt must beSome(42)
    }

    "return None for invalid integer strings" in {
      "boink".toIntOpt must beNone
    }
  }
}
