package com.github.t3hnar.scalax

/**
 * @author Yaroslav Klymko
 */
object StringOption extends (String => Option[String]) {
  def apply(s: String): Option[String] = Option(s) map (_.trim) filter (_.nonEmpty)
}
