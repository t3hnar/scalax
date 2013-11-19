package scalax.examples

/**
 * @author Yaroslav Klymko
 */
object RichPartialFunctionExample {
  import scalax.RichPartialFunction

  val pf: PartialFunction[Int, String] = {
    case 0 => "0"
    case 1 => "1"
  }

  pf.isDefinedAt(0) // true
  pf.isDefinedAt(1) // true

  val pf2 = pf.filter(_ == 0)

  pf2.isDefinedAt(0) // true
  pf2.isDefinedAt(1) // false
}