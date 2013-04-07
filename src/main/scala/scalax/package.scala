import reflect.ClassTag

/**
 * @author Yaroslav Klymko
 */
package object scalax {
  implicit class RichAny[A](val self: A) extends AnyVal {
    def asInstanceOfOpt[T <: A](implicit tag: ClassTag[T]): Option[T] = asInstanceOfOptUnsafe[T]

    /**
     *  "".asInstanceOfOptUnsafe[Int] compiles
     *  "".asInstanceOfOpt[Int] does not compile
     */
    def asInstanceOfOptUnsafe[T](implicit tag: ClassTag[T]): Option[T] = tag.unapply(self)
  }

  implicit class RichEnum[T <: Enumeration](val self: T) extends AnyVal {
    def withNameOpt(name: String): Option[T#Value] = self.values.find(_.toString == name)
  }
}
