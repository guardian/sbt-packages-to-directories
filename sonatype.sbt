sonatypeProfileName := "com.gu"
publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/guardian/sbt-packages-to-directories"))
scmInfo := Some(ScmInfo(url("https://github.com/guardian/sbt-packages-to-directories"), "scm:git@github.com:guardian/sbt-packages-to-directories.git"))
developers := List(
  Developer(id="mario-galic", name="Mario Galic", email="", url=url("https://github.com/mario-galic")),
)
publishTo := sonatypePublishTo.value