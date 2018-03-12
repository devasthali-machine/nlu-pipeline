name := "nlu-intent-toolkit"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.google.cloud" % "google-cloud-dialogflow" % "0.37.0-alpha"

libraryDependencies += "com.google.cloud" % "google-cloud-logging-logback" % "0.37.0-alpha"

//A Maven plugin that sets various useful properties detected from ${os.name} and ${os.arch} properties.
libraryDependencies += "kr.motd.maven" % "os-maven-plugin" % "1.5.0.Final"

//https://github.com/grpc/grpc-java/blob/master/SECURITY.md
libraryDependencies += "io.netty" % "netty-tcnative-boringssl-static" % "2.0.7.Final"

//
libraryDependencies += "org.mortbay.jetty.alpn" % "jetty-alpn-agent" % "2.0.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

resolvers += "mvn central" at "http://central.maven.org/maven2/"

assemblyJarName := "nlu-intent-toolkit.jar"