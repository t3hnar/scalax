package com.github.t3hnar.scalax

import org.specs2.mutable.Specification
import scala.util.{ Failure, Success }

class RichEitherSpec extends Specification {
  "Either.toTry" should {
    "return Failure on left with throwable" in {
      val e = new RuntimeException
      val either: Either[Throwable, Int] = Left(e)
      either.toTry mustEqual Failure(e)
    }

    "return Success on right" in {
      val either: Either[Throwable, Int] = Right(1)
      either.toTry mustEqual Success(1)
    }
  }
}
