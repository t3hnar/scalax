package scalax

/**
 * @author Yaroslav Klymko
 */
trait AnyImplicits {

  implicit def richAny(any: Any) = new RichAny(any)

  class RichAny(any: Any) {
    def asInstanceOfOpt[T](implicit m: Manifest[T]): Option[T] =
      if (any == null) None
      else if (Manifest.classType(any.getClass) <:< m) Some(any.asInstanceOf[T])
      else None
  }

}
