package com.github.t3hnar.scalax

import org.specs2.mutable.Specification
import scala.util.Try

class RichTrySpec extends Specification {
  "Try.fold" should {
    "fold on success" in {
      Try("10".toInt).fold(_ => "fail")(_.toString) mustEqual "10"
    }

    "fold on failure" in {
      Try("abc".toInt).fold(_ => "fail")(_.toString) mustEqual "fail"
    }
  }

  "Try.toEither" should {
    "return Right on success" in {
      Try("10".toInt).toEither mustEqual Right(10)
    }

    "return Left on failure" in {
      Try("abc".toInt).toEither must beLike { case Left(_: NumberFormatException) => ok }
    }
  }
}
