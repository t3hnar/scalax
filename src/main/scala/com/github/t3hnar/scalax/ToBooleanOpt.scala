package com.github.t3hnar.scalax

object ToBooleanOpt {
  private val map: Map[String, Boolean] = Map(
    "true" -> true,
    "false" -> false,
    "+" -> true,
    "-" -> false,
    "on" -> true,
    "off" -> false,
    "yes" -> true,
    "no" -> false)

  def apply(x: String): Option[Boolean] = map.get(x.toLowerCase)
}
