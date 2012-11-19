package scalax

/**
 * @author Yaroslav Klymko
 */
object StringOption {
  def apply(s: String): Option[String] = s match {
    case null | "" => None
    case _ => Some(s)
  }
}