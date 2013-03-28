package scalax

/**
 * @author Yaroslav Klymko
 */
trait AnyImplicits {
  implicit def richAny[A](any: A) = new RichAny(any)

  class RichAny[A](self: A) {
    def asInstanceOfOpt[T <: A](implicit m: Manifest[T]): Option[T] =
      if (self == null) None
      else if (Manifest.classType(self.getClass) <:< m) Some(self.asInstanceOf[T])
      else None
  }
}
