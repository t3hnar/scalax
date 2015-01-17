package com.github.t3hnar.scalax

import org.specs2.mutable.Specification

class RichSetMapSpec extends Specification {
  val empty = Map[String, Set[Int]]()
  "RichSetMap.getOrEmpty" should {
    "return an empty set if no found" in {
      empty.getOrEmpty("1") must beEmpty
    }

    "return set" in {
      val map = Map("1" -> Set(1, 2))
      map.getOrEmpty("1") mustEqual Set(1, 2)
    }
  }

  "RichSetMap.updatedSet" should {
    "add key with new set if not found" in {
      val expected = Map("1" -> Set(1, 2))
      empty.updatedSet("1", _ => Set(1, 2)) mustEqual expected
    }

    "add elements to found set" in {
      val expected = Map("1" -> Set(1, 2))
      Map("1" -> Set(1)).updatedSet("1", _ + 2) mustEqual expected
    }

    "remove key if updated set is empty" in {
      Map("1" -> Set(1)).updatedSet("1", _ - 1) mustEqual empty
    }
  }
}
