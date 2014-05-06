import SonatypeKeys._
import scalariform.formatter.preferences._

name := "scalax"

organization := "com.github.t3hnar"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.0")

licenses := Seq(("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))

homepage := Some(new URL("https://github.com/t3hnar/scalax"))

scalacOptions := Seq("-encoding", "UTF-8", "-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

libraryDependencies += "org.specs2" %% "specs2" % "2.3.11" % "test"

organization := "com.github.t3hnar"

profileName := "t3hnar"

pomExtra := {
  <scm>
    <url>git@github.com:t3hnar/scalax.git</url>
    <developerConnection>scm:git:git@github.com:t3hnar/scalax.git</developerConnection>
    <connection>scm:git:git@github.com:t3hnar/scalax.git</connection>
  </scm>
  <developers>
    <developer>
      <id>t3hnar</id>
      <name>Yaroslav Klymko</name>
      <email>t3hnar@gmail.com</email>
    </developer>
  </developers>
}

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignParameters, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)

scalariformSettings

sonatypeSettings

releaseSettings