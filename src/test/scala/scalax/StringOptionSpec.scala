package scalax

import org.specs2.mutable.SpecificationWithJUnit

/**
 * @author Yaroslav Klymko
 */
class StringOptionSpec extends SpecificationWithJUnit {
  "StringOption" should {
    "return None if null" in {
      StringOption(null) must beNone
    }

    "return None if empty string" in {
      StringOption("") must beNone
    }

    "return None if string consists of whitespace chars" in {
      StringOption("\t\n\r ") mustEqual None
    }

    "return Some if nonempty string" in {
      StringOption("nonempty") mustEqual Some("nonempty")
    }

    "trim spaces if nonempty string" in {
      StringOption("\t\nnonempty\r ") mustEqual Some("nonempty")
    }
  }
}
