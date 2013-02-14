package scalax

/**
 * @author Yaroslav Klymko
 */
object StringOption {
  def apply(s: String): Option[String] =
    if (s == null) None
    else s.trim match {
      case "" => None
      case x => Some(x)
    }
}