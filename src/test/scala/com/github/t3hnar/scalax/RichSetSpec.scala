package com.github.t3hnar.scalax

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

/**
 * @author Yaroslav Klymko
 */
class RichSetSpec extends Specification {
  "RichSet.collate" should {
    "returns None if sets are empty" in new CollateScope {
      collate()() must beNone
    }

    "return None if sets are equal" in new CollateScope {
      collate(
        Entity(1, "1"))(
          Entity(1, "1")) must beNone
    }

    "return updated item" in new CollateScope {
      collate(
        Entity(1, "1"))(
          Entity(1, "2")) must beSome((Set(), Set(Entity(1, "2")), Set()))
    }

    "return deleted item" in new CollateScope {
      collate(
        Entity(1, "1"))() must beSome((Set(), Set(), Set(1)))
    }

    "return added item" in new CollateScope {
      collate(
        Entity(1, "1"))(
          Entity(1, "1"), Entity(2, "2")) must beSome((Set(Entity(2, "2")), Set(), Set()))
    }

    "return added, updated and deleted" in new CollateScope {
      collate(
        Entity(1, "1"), Entity(2, "2"), Entity(3, "3"))(
          Entity(1, "1"), Entity(2, "u"), Entity(4, "4")) must beSome((Set(Entity(4, "4")), Set(Entity(2, "u")), Set(3)))
    }
  }

  trait CollateScope extends Scope {
    def collate(x: Entity*)(y: Entity*): Option[(Set[Entity], Set[Entity], Set[Int])] =
      x.toSet.collate(y.toSet)(_.id)

    case class Entity(id: Int, name: String)
  }
}
