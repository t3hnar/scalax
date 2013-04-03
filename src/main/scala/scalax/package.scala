import reflect.ClassTag

/**
 * @author Yaroslav Klymko
 */
package object scalax {

  implicit class RichAny[A](val self: A) extends AnyVal {
    def asInstanceOfOpt[T <: A](implicit tag: ClassTag[T]): Option[T] = tag.unapply(self)
  }
}
