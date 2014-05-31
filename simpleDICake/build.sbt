name := """simpleDICake"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  ws,
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)
