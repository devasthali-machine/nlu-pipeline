name := "nlu-api"

version := "1.0"

scalaVersion := "2.12.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.mavenLocal

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-http" % "10.0.10",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
    "org.apache.kafka" %% "kafka" % "1.0.0",
    "org.apache.kafka" % "kafka-clients" % "1.0.0",
    "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  )
}
