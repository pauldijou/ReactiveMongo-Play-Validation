import sbt._
import sbt.Keys._

object BuildSettings {
  val buildVersion = "0.11.0-SNAPSHOT"

  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "org.reactivemongo",
    version := buildVersion,
    scalaVersion := "2.11.2",
    crossScalaVersions := Seq("2.11.2", "2.10.4"),
    crossVersion := CrossVersion.binary
  ) ++ Publish.settings
}

object Publish {
  def targetRepository: Project.Initialize[Option[sbt.Resolver]] = version { (version: String) =>
    val nexus = "https://oss.sonatype.org/"
    if (version.trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  }
  lazy val settings = Seq(
    publishMavenStyle := true,
    publishTo <<= targetRepository,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("http://reactivemongo.org")),
    pomExtra := (
      <scm>
        <url>git://github.com/zenexity/Play-ReactiveMongo.git</url>
        <connection>scm:git://github.com/zenexity/Play-ReactiveMongo.git</connection>
      </scm>
      <developers>
        <developer>
          <id>sgodbillon</id>
          <name>Stephane Godbillon</name>
          <url>http://stephane.godbillon.com</url>
        </developer>
        <developer>
          <id>pauldijou</id>
          <name>Paul Dijou</name>
          <url>http://pauldijou.com</url>
        </developer>
      </developers>)
  )
}

object ProjectBuild extends Build {
  import BuildSettings._

  lazy val reactivemongo = Project(
    "ReactiveMongo-Play-Validation",
    file("."),
    settings = buildSettings ++ Seq(
      resolvers := Seq(
        "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
        "Sonatype" at "https://oss.sonatype.org/content/groups/public/",
        "Typesafe repository releases" at "https://repo.typesafe.com/typesafe/releases/",
        "Typesafe repository snapshots" at "https://repo.typesafe.com/typesafe/snapshots/",
        "JTO snapshots" at "https://raw.github.com/jto/mvn-repo/master/snapshots"
      ),
      libraryDependencies ++= Seq(
        "org.reactivemongo" %% "play2-reactivemongo" % "0.11.0-SNAPSHOT",
        "com.typesafe.play" %% "play" % "2.3.0" % "provided" cross CrossVersion.binary,
        "io.github.jto" %% "validation-core" % "1.0-1c770f4" cross CrossVersion.binary,
        "com.typesafe.play" %% "play-test" % "2.3.0" % "test" cross CrossVersion.binary,
        "org.specs2" % "specs2" % "2.3.12" % "test" cross CrossVersion.binary,
        "junit" % "junit" % "4.8" % "test" cross CrossVersion.Disabled,
        "org.apache.logging.log4j" % "log4j-to-slf4j" % "2.0-beta9"
      )
    )
  )
}
