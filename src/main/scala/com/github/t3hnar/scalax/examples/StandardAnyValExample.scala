package com.github.t3hnar.scalax.examples

object StandardAnyValExample {

  import com.github.t3hnar.scalax.StandardAnyVal

  StandardAnyVal.unapply(()) // Some(())
  StandardAnyVal.unapply(true) // Some(true)
  StandardAnyVal.unapply(' ') // Some( )
  StandardAnyVal.unapply(1) // Some(1)
  StandardAnyVal.unapply("") // None
}