package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

import scala.util.{ Failure, Success }

class RichTrySpec extends Specification {
  type Try[A] = RichTry[A]
  "Try.fold" should {
    "return message with value" in {
      val successTry: Try[Int] = Success(1)
      val valueMessage = successTry.fold(e => s"Error:$e")(value => s"Value:$value")
      valueMessage shouldEqual "Value:1"
    }

    "return message with error" in {
      val e = new RuntimeException
      val failureTry: Try[Throwable] = Failure(e)
      val errorMessage = failureTry.fold(e => s"Error:$e")(value => s"Value:$value")
      errorMessage shouldEqual "Error:java.lang.RuntimeException"
    }
  }

  "Try.toEither" should {
    "return Right" in {
      val successTry: Try[Int] = Success(1)
      val right = successTry.toEither
      right shouldEqual Right(1)
    }

    "return Left" in {
      val e = new RuntimeException
      val failureTry: Try[Throwable] = Failure(e)
      val right = failureTry.toEither
      right shouldEqual Left(e)
    }
  }
}