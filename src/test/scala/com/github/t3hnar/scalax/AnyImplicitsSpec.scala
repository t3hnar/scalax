package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

/**
 * @author Yaroslav Klymko
 */
class AnyImplicitsSpec extends Specification {
  "AnyImplicitsSpec" should {
    "asInstanceOfOpt" in {
      (null: Any).asInstanceOfOpt[String] must beNone

      //      (java.lang.Boolean.TRUE: Any).asInstanceOfOpt[Boolean] must beSome(true)
      (true: Any).asInstanceOfOpt[java.lang.Boolean] must beSome(true)
      //      (true: Any).asInstanceOfOpt[Boolean] must beSome(true)

      ("string": Any).asInstanceOfOpt[String] must beSome
      ("string": Any).asInstanceOfOpt[Any] must beSome
      ("string": Any).asInstanceOfOpt[Int] must beNone

      (new A {}: Any).asInstanceOfOpt[A] must beSome
      (new A {}: Any).asInstanceOfOpt[Any] must beSome
      (new A {}: Any).asInstanceOfOpt[B] must beNone

      (new A with B {}: Any).asInstanceOfOpt[A] must beSome
      (new A with B {}: Any).asInstanceOfOpt[B] must beSome
      (new A with B {}: Any).asInstanceOfOpt[A] must beSome
      (new A with B {}: Any).asInstanceOfOpt[C] must beNone

      (new C {}: Any).asInstanceOfOpt[A] must beSome
      (new C {}: Any).asInstanceOfOpt[B] must beSome
      (new C {}: Any).asInstanceOfOpt[Int] must beNone
    }
  }

  trait A
  trait B
  trait C extends A with B
}