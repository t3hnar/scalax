# ScalaX [![Build Status](https://secure.travis-ci.org/t3hnar/scalax.png)](http://travis-ci.org/t3hnar/scalax)

Extension for [Scala](http://www.scala-lang.org) library

## Examples

### StringOption

```scala
import com.github.t3hnar.scalax.StringOption

StringOption(null) // None
StringOption("") // None
StringOption("\t\n\r ") // None
StringOption("nonempty") // Some("nonempty")
StringOption("\t\nnonempty\r ") // Some("nonempty")
```

### RichString

```scala
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
```

### asInstanceOfOpt

```scala
import com.github.t3hnar.scalax.RichAny

(null: Any).asInstanceOfOpt[String] // None
("string": Any).asInstanceOfOpt[String] // Some
```

### RichEnum

Adds `withNameOpt` method additionally to default `withName`

```scala
  object Color extends Enumeration {
    val Green, Blue, Red = Value
  }

  import com.github.t3hnar.scalax.RichEnum

  Color.withNameOpt("Green") // Some(Green)
  Color.withNameOpt("Black") // None
```

### RichSet

Adds `collate` method to set. `collate` tries to compare sets of items in detail
and returns items which were added, updated (key relative) and deleted in `s2` regarding `s1`

```scala
  import com.github.t3hnar.scalax.RichSet

  case class Entity(id: Int, name: String)

  val s1 = Set(Entity(1, "1"), Entity(2, "2"), Entity(3, "3"))
  val s2 = Set(Entity(1, "1"), Entity(2, "u"), Entity(4, "4"))

  s1.collate(s2)(_.id) // Some(Set(Entity(4, "4")), Set(Entity(2, "u")), Set(3))
```

### RichDuration

```scala
  import com.github.t3hnar.scalax.RichDuration
  import concurrent.duration._

  60.seconds.toCoarsest // 1.minute
  60.minutes.toCoarsest // 1.hour
  180.minutes.toCoarsest // 3.hours
  24.hours.toCoarsest // 1.day
  Duration.Inf.toCoarsest // Duration.Inf
  Duration.MinusInf.toCoarsest // Duration.MinusInf
```

### RichPartialFunction
```scala
  import com.github.t3hnar.scalax.RichPartialFunction

  val pf1: PartialFunction[Int, String] = {
    case 0 => "0"
    case 1 => "1"
  }

  val pf2 = pf1.filter(_ == 0)

  pf2.isDefinedAt(0) // true
  pf2.isDefinedAt(1) // false
```

### RichTry

```scala
  import com.github.t3hnar.scalax.RichTry
  
  Try("10".toInt).fold("NumberFormatException for " + _)("Valid int: " + _)
```

### ExpiringCache

```scala
  import com.github.t3hnar.scalax.util.ExpiringCache

  val cache = new ExpiringCache[Int, Int](duration = 5,
                                        unit = TimeUnit.SECONDS,
                                        queryOverflow = 3)
  cache.put(0,0)
  cache.get(0) // Some(0)

  // after 5 seconds
  cache.get(0) // None, however it is not cleaned up yet, need one more query to go

  cache.get(0) // None, now it's cleaned up as we reached `queryOverflow` limit
```

### FieldsMap

Method to get map of field name - value pairs, implemented with help of [Scala Macros](http://docs.scala-lang.org/overviews/macros/overview.html)
It means that method call has no performance drawback at runtime.

```scala
import com.github.t3hnar.scalax.FieldsMap

case class User(name: String, age: Int, address: Option[String]) extends FieldsMap
val user = User("Chuck", 30, None)
user.fieldsMap // Map("name" -> "Chuck", "age" -> 30, "address" -> None)
```

## Setup

```scala
libraryDependencies += "com.github.t3hnar" %% "scalax" % "2.2"
```
