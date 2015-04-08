package com.github.t3hnar.scalax.examples

object PrimitiveExample {

  import com.github.t3hnar.scalax.Primitive

  Primitive.unapply(()) // None
  Primitive.unapply(true) // Some(true)
  Primitive.unapply(' ') // Some( )
  Primitive.unapply(1) // Some(1)
  Primitive.unapply("") // None
}
