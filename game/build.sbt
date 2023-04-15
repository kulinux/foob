val scala3Version = "3.2.2"

val http4sVersion = "0.23.18"

lazy val root = project
  .in(file("."))
  .configs(IntegrationTest)
  .settings(
    name := "Game",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    Defaults.itSettings,
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.15",
      "org.scalatest" %% "scalatest" % "3.2.15" % "it,test",
      "org.scalatestplus" %% "easymock-5-1" % "3.2.15.0" % Test,
      "org.typelevel" %% "cats-core" % "2.9.0",
      "dev.optics" %% "monocle-core" % "3.1.0",
      "dev.optics" %% "monocle-macro" % "3.1.0",
      "com.softwaremill.sttp.client3" %% "core" % "3.8.14",
      "com.softwaremill.sttp.client3" %% "upickle" % "3.8.14",

      "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl"          % http4sVersion,
    )
  )
