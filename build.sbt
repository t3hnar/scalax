import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

name := "scalax"

organization := "com.github.t3hnar"

scalaVersion := "2.12.1"

crossScalaVersions := Seq("2.12.1", "2.11.8", "2.10.6")

releaseCrossBuild := true

licenses := Seq(("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))

homepage := Some(new URL("https://github.com/t3hnar/scalax"))

scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture")

libraryDependencies += "org.specs2" %% "specs2-core" % "3.8.6" % Test

organization := "com.github.t3hnar"

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

coverageExcludedPackages := "com.github.t3hnar.scalax.examples.*"

releasePublishArtifactsAction := PgpKeys.publishSigned.value