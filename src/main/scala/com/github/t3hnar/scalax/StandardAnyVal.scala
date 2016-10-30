package com.github.t3hnar.scalax

import scala.PartialFunction.condOpt

object StandardAnyVal {
  def unapply(x: Any): Option[AnyVal] = condOpt(x) {
    case x: Unit    => x
    case x: Boolean => x
    case x: Byte    => x
    case x: Char    => x
    case x: Short   => x
    case x: Int     => x
    case x: Long    => x
    case x: Float   => x
    case x: Double  => x
  }
}