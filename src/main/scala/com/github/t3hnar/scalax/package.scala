package com.github.t3hnar

import scala.concurrent.duration.Duration.Infinite
import scala.concurrent.duration.{ Duration, FiniteDuration }
import scala.reflect.ClassTag
import scala.util.{ Failure, Success, Try }

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
      case x: FiniteDuration =>
        import scala.concurrent.duration._

        def loop(length: Long, unit: TimeUnit): FiniteDuration = {
          def coarserOrThis(coarser: TimeUnit, divider: Int) =
            if (length % divider == 0) loop(length / divider, coarser)
            else if (unit == x.unit) x
            else FiniteDuration(length, unit)

          unit match {
            case DAYS         => FiniteDuration(length, unit)
            case HOURS        => coarserOrThis(DAYS, 24)
            case MINUTES      => coarserOrThis(HOURS, 60)
            case SECONDS      => coarserOrThis(MINUTES, 60)
            case MILLISECONDS => coarserOrThis(SECONDS, 1000)
            case MICROSECONDS => coarserOrThis(MILLISECONDS, 1000)
            case NANOSECONDS  => coarserOrThis(MICROSECONDS, 1000)
          }
        }

        if (x.unit == DAYS || x.length == 0) x.asInstanceOf[T]
        else loop(x.length, x.unit).asInstanceOf[T]
    }
  }

  implicit class RichPartialFunction[A, +B](val self: PartialFunction[A, B]) extends AnyVal {
    def filter(f: A => Boolean): PartialFunction[A, B] = new PartialFunctionFilter[A, B](self, f)
    def filterNot(f: A => Boolean): PartialFunction[A, B] = filter(x => !f(x))
  }

  implicit class RichString(val self: String) extends AnyVal {

    def toByteOpt: Option[Byte] = numeric(_.toByte)

    def toIntOpt: Option[Int] = numeric(_.toInt)

    def toLongOpt: Option[Long] = numeric(_.toLong)

    def toFloatOpt: Option[Float] = numeric(_.toFloat)

    def toDoubleOpt: Option[Double] = numeric(_.toDouble)

    def toBigInt: BigInt = BigInt(self)

    def toBigIntOpt: Option[BigInt] = numeric(_.toBigInt)

    def toBigDecimal: BigDecimal = BigDecimal(self)

    def toBigDecimalOpt: Option[BigDecimal] = numeric(_.toBigDecimal)

    def toBooleanOpt: Option[Boolean] = StringOption(self).flatMap(ToBooleanOpt(_))

    private def numeric[T](f: String => T): Option[T] = try StringOption(self).map(f) catch {
      case _: NumberFormatException => None
    }
  }

  implicit class RichSetMap[M, S](val self: Map[M, Set[S]]) extends AnyVal {
    def getOrEmpty(m: M): Set[S] = self.getOrElse(m, Set.empty)

    def updatedSet(m: M, f: Set[S] => Set[S]): Map[M, Set[S]] = {
      val set = f(self getOrEmpty m)
      if (set.isEmpty) self - m
      else self + (m -> set)
    }
  }

  implicit class RichClass(val self: Class[_]) extends AnyVal {
    def simpleName: String = {
      val xs = self.getName.split("\\$")
      if (xs.size > 1) xs.last else self.getSimpleName
    }
  }

  implicit class RichOption[T](val self: Option[T]) extends AnyVal {
    def orError(msg: => String): T = self getOrElse sys.error(msg)
  }
}