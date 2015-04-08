package com.github.t3hnar.scalax
package examples

object RichEitherExample {
  {
    val either: Either[Throwable, Int] = Left(new RuntimeException)
    either.toTry
  }

  {
    val either: Either[Throwable, Int] = Right(1)
    either.toTry
  }
}
