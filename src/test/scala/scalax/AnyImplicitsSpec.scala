package scalax

import org.specs2.mutable.SpecificationWithJUnit

/**
 * @author Yaroslav Klymko
 */
class AnyImplicitsSpec extends SpecificationWithJUnit {
  "AnyImplicitsSpec" should {
    "asInstanceOfOpt" in {
      (null: Any).asInstanceOfOpt[String] must beNone

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