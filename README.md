# ScalaX [![Build Status](https://secure.travis-ci.org/t3hnar/scalax.png)](http://travis-ci.org/t3hnar/scalax)

Extension for [Scala](http://www.scala-lang.org) library

## Examples

### StringOption

```scala
import scalax._

StringOption(null) // None
StringOption("") // None
StringOption("\t\n\r ") // None
StringOption("nonempty") // Some("nonempty")
StringOption("\t\nnonempty\r ") // Some("nonempty")
```

### asInstanceOfOpt

```scala
import scalax._

(null: Any).asInstanceOfOpt[String] // None
("string": Any).asInstanceOfOpt[String] // Some
```

### RichEnum

Adds `withNameOpt` method additionally to default `withName`

```scala
  object Color extends Enumeration {
    val Green, Blue, Red = Value
  }

  import scalax._

  Color.withNameOpt("Green") // Some(Green)
  Color.withNameOpt("Black") // None
```

### ExpiringCache

```scala
import scalax.util.ExpiringCache

val cache = new ExpiringCache[Int, Int](duration = 5,
                                        unit = TimeUnit.SECONDS,
                                        queryOverflow = 3)
cache.put(0,0)
cache.get(0) // Some(0)

// after 5 seconds
cache.get(0) // None, however it is not cleaned up yet, need one more query to go

cache.get(0) // None, now it's cleaned up as we reached `queryOverflow` limit
```

## Setup

1. Add this repository to your pom.xml:
```xml
    <repository>
        <id>thenewmotion</id>
        <name>The New Motion Repository</name>
        <url>http://nexus.thenewmotion.com/content/repositories/releases-public</url>
    </repository>
```

2. Add dependency to your pom.xml:
```xml
    <dependency>
        <groupId>ua.t3hnar.scalax</groupId>
        <artifactId>scalax_2.10</artifactId>
        <version>1.3</version>
    </dependency>
```