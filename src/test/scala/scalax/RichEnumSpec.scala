package scalax

import org.specs2.mutable.SpecificationWithJUnit

/**
 * @author Yaroslav Klymko
 */
class RichEnumSpec extends SpecificationWithJUnit {
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
