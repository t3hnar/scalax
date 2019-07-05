package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class RichClassSpec extends Specification {
  "RichClass" should {
    "return simple name for outer class" in {
      classOf[RichClassSpec].simpleName shouldEqual "RichClassSpec"
    }

    "return simple name for inner1 class" in {
      classOf[RichClassSpec.Inner1].simpleName shouldEqual "Inner1"
    }

    "return simple name for inner2 class" in {
      val clazz = classOf[RichClassSpec.Inner1.Inner2]
      //      clazz.getSimpleName must throwAn[InternalError]
      clazz.simpleName shouldEqual "Inner2"
    }
  }
}

object RichClassSpec {
  class Inner1
  object Inner1 {
    class Inner2
  }
}