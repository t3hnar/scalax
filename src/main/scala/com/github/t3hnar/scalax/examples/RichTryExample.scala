package com.github.t3hnar.scalax
package examples

import scala.util.Try

object RichTryExample {

  Try("10".toInt).fold("NumberFormatException for " + _)("Valid int: " + _)

  Try("10".toInt).toEither

  Try("ABC".toInt).toEither
}