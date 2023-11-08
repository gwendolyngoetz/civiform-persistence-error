name := """play-java-hello-world-tutorial"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.12"

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  "com.jayway.jsonpath" % "json-path" % "2.8.0",

  // Database and database testing libraries
  "org.postgresql" % "postgresql" % "42.6.0",
  "com.h2database" % "h2" % "2.2.224" % Test,

  // To provide an implementation of JAXB-API, which is required by Ebean.
  "javax.xml.bind" % "jaxb-api" % "2.3.1",
  "javax.activation" % "activation" % "1.1.1",
  "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.9",
)
