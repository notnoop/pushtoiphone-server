import sbt._

class LiftProject(info: ProjectInfo) extends DefaultWebProject(info) {
  val liftVersion = "2.2"

  // uncomment the following if you want to use the snapshot repo
  // val scalatoolsSnapshot = ScalaToolsSnapshots

  // If you're using JRebel for Lift development, uncomment
  // this line
  override def scanDirectories = Nil

  override def libraryDependencies = Set(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "org.mortbay.jetty" % "jetty" % "6.1.22" % "test->default",
    "junit" % "junit" % "4.5" % "test->default",
    "ch.qos.logback" % "logback-classic" % "0.9.26",
    "org.scala-tools.testing" %% "specs" % "1.6.6" % "test->default"
    //"org.specs2" %% "specs2" % "1.0.1" % "test->default"
  ) ++ super.libraryDependencies

  // More configuration
  val java_apns = "com.notnoop.apns" % "apns" % "0.1.6"
//  val rogue = "com.foursquare" % "rogue_2.8.0" % "1.0.3"

  val snapshots = "snapshots" at "http://scala-tools.org/repo-snapshots"

  val h2db = "com.h2database" % "h2" % "1.2.138" % "test->default"

}
