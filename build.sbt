lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.10"
    )),
    name := "scala-talk"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test
