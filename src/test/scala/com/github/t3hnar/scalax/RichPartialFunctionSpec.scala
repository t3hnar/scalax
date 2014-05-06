package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

/**
 * @author Yaroslav Klymko
 */
class RichPartialFunctionSpec extends Specification {
  "PartialFunction.filter" should {
    "return new partial that will work for input that satisfy filter" in {
      val pf: PartialFunction[Int, String] = {
        case 0 => "0"
        case 1 => "1"
      }

      pf.lift(0) must beSome("0")
      pf.lift(1) must beSome("1")

      pf.filter(_ == 0).lift(0) must beSome("0")
      pf.filter(_ == 0).lift(1) must beNone
    }
  }

  "PartialFunction.filterNot" should {
    "return new partial that will for input that not satisfy filter" in {
      val pf: PartialFunction[Int, String] = {
        case 0 => "0"
        case 1 => "1"
      }

      pf.lift(0) must beSome("0")
      pf.lift(1) must beSome("1")

      pf.filterNot(_ == 0).lift(0) must beNone
      pf.filterNot(_ == 0).lift(1) must beSome("1")
    }
  }
}
