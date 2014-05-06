package com.github.t3hnar.scalax
package examples

/**
 * @author Yaroslav Klymko
 */
object RichEnumExample {
  object Color extends Enumeration {
    val Green, Blue, Red = Value
  }

  import com.github.t3hnar.scalax.RichEnum

  Color.withNameOpt("Green") // Some(Green)
  Color.withNameOpt("Black") // None
}