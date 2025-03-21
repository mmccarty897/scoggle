val scala3Version = "3.6.4"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scoggle",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.15.4" % "test"
  )
