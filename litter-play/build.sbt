name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies += jdbc
libraryDependencies += cache
libraryDependencies += ws
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % Test

libraryDependencies += "com.typesafe.play" %% "play-slick" % "2.0.2"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.1.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.40"
