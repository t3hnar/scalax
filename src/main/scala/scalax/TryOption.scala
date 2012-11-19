package scalax

/**
 * @author Yaroslav Klymko
 */
object TryOption {
  def apply[T](body: => T): Option[T] = try Option(body) catch {
    case _: Exception => None
  }
}