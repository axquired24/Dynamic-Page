
enablePlugins(ScalaJSPlugin)

name := "scalajs-latihan"

version := "1.0"

scalaVersion := "2.12.3"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

resolvers ++= Seq(
//  "Grails Repo"         at  "https://repo.grails.org/grails/plugins/"
////"Maven Repo"              at "https://mvnrepository.com/artifact/be.doeraene/scalajs-jquery_sjs0.6_2.11",
////"typesafe repo"           at "http://repo.typesafe.com/typesafe/releases/",
////"Ansvia repo"             at "http://scala.repo.ansvia.com/releases/",
////"Ansvia snapshot repo"    at "http://scala.repo.ansvia.com/nexus/content/repositories/snapshots",
//"Sonatype snapshots"      at "https://oss.sonatype.org/content/repositories/snapshots",
//"Sonatype releases"       at "https://oss.sonatype.org/content/repositories/releases"
)


libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalatags"                 % "0.6.7",
  "be.doeraene" %%% "scalajs-jquery"            % "0.9.2",
  "org.scala-js" %%% "scalajs-dom"              % "0.9.2"
////  "be.doeraene" %%% "scalajs-jquery"            % "0.8.1",
//  "com.lihaoyi" %%% "scalarx"                   % "0.2.7"
)

//libraryDependencies += "com.lihaoyi" %% "scalatags" % "0.6.7"
//libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.2"
//libraryDependencies += "org.grails.plugins" % "slug-generator" % "0.5"