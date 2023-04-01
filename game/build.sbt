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
      "org.scalatestplus" %% "easymock-5-1" % "3.2.15.0" % Test,
      "org.typelevel" %% "cats-core" % "2.9.0",
      "eu.timepit" %% "refined" % "0.10.3", //refinment map
      "eu.timepit" %% "refined-cats" % "0.10.3",
      "org.typelevel" %% "cats-effect" % "3.4.8",
      "dev.optics" %% "monocle-core"  % "3.1.0",
      "dev.optics" %% "monocle-macro" % "3.1.0"
    )
  )
