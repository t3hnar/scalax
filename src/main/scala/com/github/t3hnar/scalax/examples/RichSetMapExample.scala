package com.github.t3hnar.scalax.examples

object RichSetMapExample {
  import com.github.t3hnar.scalax.RichSetMap

  val map = Map(1 -> Set(1))
  map.getOrEmpty(1) // Set()
  map.updatedSet(1, _ - 1) // Map()
  map.updatedSet(1, _ + 2) // Map(1 -> Set(1, 2))
  map.updatedSet(2, _ + 2) // Map(1 -> Set(1), 2 -> Set(2))
  map.updatedSet(1, _ => Set(1, 2, 3)) // Map(1 -> Set(1, 2, 3))
}