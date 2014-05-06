package com.github.t3hnar.scalax.examples

/**
 * @author Yaroslav Klymko
 */
object RichPartialFunctionExample {
  import com.github.t3hnar.scalax.RichPartialFunction

  val pf1: PartialFunction[Int, String] = {
    case 0 => "0"
    case 1 => "1"
  }

  val pf2 = pf1.filter(_ == 0)

  pf2.isDefinedAt(0) // true
  pf2.isDefinedAt(1) // false
}