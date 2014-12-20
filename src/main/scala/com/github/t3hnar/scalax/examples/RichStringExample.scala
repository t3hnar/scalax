package com.github.t3hnar.scalax.examples

object RichStringExample {

  import com.github.t3hnar.scalax.RichString

  "123".toByteOpt // Some(123)
  "1-2-3".toByteOpt // None

  "123".toIntOpt // Some(123)
  "1-2-3".toIntOpt // None

  "123".toLongOpt // Some(123)
  "1-2-3".toLongOpt // None

  "123".toFloatOpt // Some(123.0)
  "1-2-3".toFloatOpt // None

  "123".toDoubleOpt // Some(123.0)
  "1-2-3".toDoubleOpt // None

  "true".toBooleanOpt // Some(true)
  "false".toBooleanOpt // Some(false)

  "+".toBooleanOpt // Some(true)
  "-".toBooleanOpt // Some(false)

  "ON".toBooleanOpt // Some(true)
  "OFF".toBooleanOpt // Some(false)

  "yes".toBooleanOpt // Some(true)
  "no".toBooleanOpt // Some(false)
}
