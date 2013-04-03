package scalax

import scala.util.Try

/**
 * @author Yaroslav Klymko
 */
@deprecated("Use scala.util.Try instead")
object TryOption {
  def apply[T](body: => T): Option[T] = Try(body).toOption
}