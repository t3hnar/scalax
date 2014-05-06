package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

/**
 * @author Yaroslav Klymko
 */
class FieldsMapSpec extends Specification {
  "FieldsMap" should {
    "return map of fields" in {

      case class User(name: String, age: Int, address: Option[String]) extends FieldsMap

      val user = User("Chuck", 30, None)

      user.fieldsMap mustEqual Map("name" -> "Chuck", "age" -> 30, "address" -> None)
    }
  }
}