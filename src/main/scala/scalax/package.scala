import reflect.ClassTag
import concurrent.duration.{FiniteDuration, Duration}
import Duration.Infinite

/**
 * @author Yaroslav Klymko
 */
package object scalax {

  implicit class RichAny[A](val self: A) extends AnyVal {
    def asInstanceOfOpt[T <: A](implicit tag: ClassTag[T]): Option[T] = asInstanceOfOptUnsafe[T]

    /**
     * "".asInstanceOfOptUnsafe[Int] compiles
     * "".asInstanceOfOpt[Int] does not compile
     */
    def asInstanceOfOptUnsafe[T](implicit tag: ClassTag[T]): Option[T] = tag.unapply(self)
  }

  implicit class RichEnum[T <: Enumeration](val self: T) extends AnyVal {
    def withNameOpt(name: String): Option[T#Value] = self.values.find(_.toString == name)
  }

  implicit class RichSet[T](val self: Set[T]) extends AnyVal {
    /**
     * @return items which were added, updated (key relative) and deleted in `other` regarding `self`
     */
    def collate[K](other: Set[T])(key: T => K): Option[(Set[T], Set[T], Set[K])] = {
      if (other == self) None
      else Some {
        val same = self intersect other
        val changed = other diff same
        val selfKeys = self.map(key)
        val (updated, created) = changed.partition(x => selfKeys contains key(x))
        val removed = selfKeys diff other.map(key)
        (created, updated, removed)
      }
    }
  }

  implicit class RichDuration[T <: Duration](val self: T) extends AnyVal {
    def toCoarsest: T = self match {
      case _: Infinite => self
      case x: FiniteDuration => Duration.fromNanos(x.toNanos).asInstanceOf[T]
    }
  }
}
