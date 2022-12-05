ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "AdventOfCode2022",
    idePackagePrefix := Some("rusticdurian.org")
  )

libraryDependencies += "dev.zio" %% "zio" % "2.0.4"
libraryDependencies += "dev.zio" %% "zio-streams" % "2.0.4"
libraryDependencies += "dev.zio" %% "zio-logging" % "2.1.5"