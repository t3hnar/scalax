package com.github.t3hnar.scalax.examples

object RichClassExample {

  import com.github.t3hnar.scalax.RichClass

  classOf[Class1].simpleName // Class1
  classOf[Class1.Class2].simpleName // Class2
  classOf[Class1.Class2.Class3].simpleName // Class3

  class Class1
  object Class1 {
    class Class2
    object Class2 {
      class Class3
    }
  }
}
