# ScalaX [![Build Status](https://secure.travis-ci.org/t3hnar/scalax.png)](http://travis-ci.org/t3hnar/scalax)

Extension for [Scala](http://www.scala-lang.org) library

## Examples

### StringOption

```scala
import scalax._

StringOption(null) // None
StringOption("") // None
StringOption("nonempty") // Some("nonempty")
```

### TryOption

```scala
import scalax._

TryOption(sys.error("Error")) // None
TryOption(assert(false)) // AssertionError
TryOption("Not an error") // Some(Not an error)
```

### asInstanceOfOpt

```scala
import scalax._

(null: Any).asInstanceOfOpt[String] // None
("string": Any).asInstanceOfOpt[String] // Some
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
        <artifactId>scalax_2.9.2</artifactId>
        <version>1.1-SNAPSHOT</version>
    </dependency>
```