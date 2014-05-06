package com.github.t3hnar.scalax

/**
 * @author Yaroslav Klymko
 */
object StringOption extends (String => Option[String]) {
  def apply(s: String): Option[String] =
    if (s == null) None
    else s.trim match {
      case "" => None
      case x => Some(x)
    }
}