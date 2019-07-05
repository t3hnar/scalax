package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class StandardAnyValSpec extends Specification {
  import StandardAnyVal.unapply
  import StandardAnyValSpec._

  "StandardAnyVal" should {
    "return some for all nine standard AnyVal's" in {
      unapply(()) must beSome(())
      unapply(true) must beSome(true)
      unapply(1: Byte) must beSome(1: Byte)
      unapply(' ') must beSome(' ')
      unapply(1: Short) must beSome(1: Short)
      unapply(1: Int) must beSome(1: Int)
      unapply(1L) must beSome(1L)
      unapply(1f) must beSome(1f)
      unapply(1d) must beSome(1d)
      unapply(new TestAnyVal(1)) must beNone
      unapply("") must beNone
    }
  }
}

object StandardAnyValSpec {
  class TestAnyVal(val x: Int) extends AnyVal
}
