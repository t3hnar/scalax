package scalax
package examples

/**
 * @author Yaroslav Klymko
 */
object RichEnumExample {
  object Color extends Enumeration {
    val Green, Blue, Red = Value
  }

  import scalax.RichEnum

  Color.withNameOpt("Green") // Some(Green)
  Color.withNameOpt("Black") // None
}