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

      case x: FiniteDuration =>
        import scala.concurrent.duration._

        def loop(length: Long, unit: TimeUnit): FiniteDuration = {
          def coarserOrThis(coarser: TimeUnit, divider: Int) =
            if (length % divider == 0) loop(length / divider, coarser)
            else if (unit == x.unit) x
            else FiniteDuration(length, unit)

          unit match {
            case DAYS => FiniteDuration(length, unit)
            case HOURS => coarserOrThis(DAYS, 24)
            case MINUTES => coarserOrThis(HOURS, 60)
            case SECONDS => coarserOrThis(MINUTES, 60)
            case MILLISECONDS => coarserOrThis(SECONDS, 1000)
            case MICROSECONDS => coarserOrThis(MILLISECONDS, 1000)
            case NANOSECONDS => coarserOrThis(MICROSECONDS, 1000)
          }
        }

        if (x.unit == DAYS || x.length == 0) x.asInstanceOf[T]
        else loop(x.length, x.unit).asInstanceOf[T]
      case _: Infinite => self

    }
  }

  implicit class RichPartialFunction[A, +B](val self: PartialFunction[A, B]) extends AnyVal {
    def filter(f: A => Boolean): PartialFunction[A, B] = new PartialFunctionFilter[A, B](self, f)
    def filterNot(f: A => Boolean): PartialFunction[A, B] = filter(x => !f(x))
  }

  /*
  * Separate class because of: "implementation restriction: nested class is not allowed in value class"
  */
  private class PartialFunctionFilter[-A, +B](pf: PartialFunction[A, B], f: A => Boolean) extends PartialFunction[A, B] {
    def apply(x: A) = pf.apply(x)
    def isDefinedAt(x: A) = f(x) && pf.isDefinedAt(x)
  }
}
