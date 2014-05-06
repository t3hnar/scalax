package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

/**
 * @author Yaroslav Klymko
 */
class RichEnumSpec extends Specification {
  "RichEnum" should {
    "withNameOpt" in {
      Color.withNameOpt("Green") must beSome(Color.Green)
      Color.withNameOpt("Black") must beNone
    }
  }

  object Color extends Enumeration {
    val Green, Blue, Red = Value
  }
}
