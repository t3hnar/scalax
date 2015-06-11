package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class RichOptionSpec extends Specification {
  "RichOption.getOrError" should {
    "return value if some" in {
      Option(1).orError("option is empty") mustEqual 1
    }

    "throw exception if none" in {
      Option.empty[Int].orError("option is empty") must throwAn[Exception]
    }
  }
}