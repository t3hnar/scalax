package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class RichStringSpec extends Specification {

  "RichString.toIntOpt" should {

    "return Some for valid integer strings" in {
      "42".toIntOpt must beSome(42)
    }

    "return None for invalid integer strings" in {
      "boink".toIntOpt must beNone
    }
  }
}
