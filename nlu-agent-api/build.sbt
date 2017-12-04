name := "nlu-api"

version := "1.0"

scalaVersion := "2.12.4"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.mavenLocal

libraryDependencies ++= {
  Seq(
    //nlp
    "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0",
    "edu.stanford.nlp" % "stanford-corenlp" % "3.8.0" classifier "models",
    "edu.stanford.nlp" % "stanford-parser" % "3.8.0",

    "com.typesafe.akka" %% "akka-http" % "10.0.10",
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10",
    "com.typesafe.akka" %% "akka-slf4j" % "2.5.7",

    "org.apache.kafka" %% "kafka" % "1.0.0",
    "org.apache.kafka" % "kafka-clients" % "1.0.0",

    //test
    "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test",
    "com.typesafe.akka" %% "akka-testkit" % "2.5.7" % "test"

  )
}
