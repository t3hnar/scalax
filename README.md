# Scalax [![Build Status](https://secure.travis-ci.org/t3hnar/scalax.png)](http://travis-ci.org/t3hnar/scalax)



## StringOption

```scala
import scalax._

```

## TryOption

```scala
import scalax._

TryOption(sys.error("Error")) // None
TryOption(assert(false)) // AssertionError
TryOption("Not an error") // Some(Not an error)
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
        <artifactId>scalax</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```