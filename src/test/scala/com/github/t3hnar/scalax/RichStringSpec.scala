package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class RichStringSpec extends Specification {

  "RichString.toIntOpt" should {
    "return Some for valid integer strings" in {
      "42".toIntOpt must beSome(42)
    }

    "return None for invalid integer strings" in {
      "test".toIntOpt must beNone
    }
  }

  "RichString.toLongOpt" should {
    "return Some for valid long strings" in {
      "42".toLongOpt must beSome(42)
    }

    "return None for invalid long strings" in {
      "test".toLongOpt must beNone
    }
  }

  "RichString.toFloat" should {
    "return Some for valid float strings" in {
      "42.1".toFloatOpt must beSome(42.1F)
    }

    "return None for invalid float strings" in {
      "test".toFloatOpt must beNone
    }
  }

  "RichString.toDoubleOpt" should {
    "return Some for valid double strings" in {
      "42.1".toDoubleOpt must beSome(42.1)
    }

    "return None for invalid double strings" in {
      "test".toDoubleOpt must beNone
    }
  }

  "RichString.toByteOpt" should {
    "return Some for valid byte strings" in {
      "42".toByteOpt must beSome(42)
    }

    "return None for invalid byte strings" in {
      "test".toByteOpt must beNone
    }
  }

  "RichString.toBigInt" should {
    "return BigInt for valid strings" in {
      "42".toBigInt mustEqual BigInt(42)
    }

    "return throw exception invalid BigInt strings" in {
      "test".toBigInt must throwA[NumberFormatException]
    }
  }

  "RichString.BigDecimal" should {
    "return BigDecimal for valid strings" in {
      "42.31".toBigDecimal mustEqual BigDecimal(42.31)
    }

    "return throw exception invalid BigDecimal strings" in {
      "test".toBigDecimal must throwA[NumberFormatException]
    }
  }

  "RichString.toBigIntOpt" should {
    "return Some for valid BigInt strings" in {
      "42".toBigIntOpt must beSome(BigInt(42))
    }

    "return None for invalid BigInt strings" in {
      "test".toBigIntOpt must beNone
    }
  }

  "RichString.toBigDecimalOpt" should {
    "return Some for valid BigDecimal strings" in {
      "42.31".toBigDecimalOpt must beSome(BigDecimal(42.31))
    }

    "return None for invalid BigDecimal strings" in {
      "test".toBigDecimalOpt must beNone
    }
  }

  "RichString.toBooleanOpt" should {
    "return Some for valid boolean strings" in {
      "\ntrue".toBooleanOpt must beSome(true)
      "false\n".toBooleanOpt must beSome(false)
      "+".toBooleanOpt must beSome(true)
      "-".toBooleanOpt must beSome(false)
      "on".toBooleanOpt must beSome(true)
      "off".toBooleanOpt must beSome(false)
      "yes".toBooleanOpt must beSome(true)
      "no".toBooleanOpt must beSome(false)

      "TRUE".toBooleanOpt must beSome(true)
      "FALSE".toBooleanOpt must beSome(false)
      "ON".toBooleanOpt must beSome(true)
      "OFF".toBooleanOpt must beSome(false)
      "YES".toBooleanOpt must beSome(true)
      "NO".toBooleanOpt must beSome(false)
    }

    "return None for invalid boolean strings" in {
      "test".toBooleanOpt must beNone
    }
  }
}
