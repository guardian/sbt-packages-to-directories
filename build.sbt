import sbtrelease.ReleaseStateTransformations._

lazy val root = (project in file("."))
  .enablePlugins(SbtPlugin)
  .settings(
    sbtPlugin := true,
    name := "sbt-packages-to-directories",
    description := "sbt plugin to make directories structure correspond to package structure",
    organization := "com.gu",
    organizationName := "The Guardian",
    scalaVersion := "2.12.11",
    Compile / doc / sources := List(),
    releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      publishArtifacts,
      setNextVersion,
      commitNextVersion,
      releaseStepCommand("sonatypeReleaseAll"),
      pushChanges
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.1.1" % Test,
      "com.github.pathikrit" %% "better-files" % "3.8.0",
    )
  )

