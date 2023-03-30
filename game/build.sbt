val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Game",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,


    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.15",
      "org.scalatest" %% "scalatest" % "3.2.15" % Test,
      "org.scalatestplus" %% "easymock-5-1" % "3.2.15.0" % Test
    )
  )
