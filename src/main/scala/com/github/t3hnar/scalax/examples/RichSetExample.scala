package com.github.t3hnar.scalax.examples

/**
 * @author Yaroslav Klymko
 */
object RichSetExample {

  import com.github.t3hnar.scalax.RichSet

  case class Entity(id: Int, name: String)

  val s1 = Set(Entity(1, "1"), Entity(2, "2"), Entity(3, "3"))
  val s2 = Set(Entity(1, "1"), Entity(2, "u"), Entity(4, "4"))

  s1.collate(s2)(_.id) // Some(Set(Entity(4, "4")), Set(Entity(2, "u")), Set(3))
}
