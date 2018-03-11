name := "nlu-intent-toolkit"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "com.google.cloud" % "google-cloud-dialogflow" % "0.37.0-alpha"

libraryDependencies += "com.google.cloud" % "google-cloud-logging-logback" % "0.37.0-alpha"

//A Maven plugin that sets various useful properties detected from ${os.name} and ${os.arch} properties.
libraryDependencies += "kr.motd.maven" % "os-maven-plugin" % "1.5.0.Final"

//https://github.com/grpc/grpc-java/blob/master/SECURITY.md
//libraryDependencies += "io.netty" % "netty-tcnative-boringssl-static" % "2.0.0.Final"
//java.lang.RuntimeException: deduplicate: different file contents found in the following:
//  /Users/a1353612/.ivy2/cache/io.grpc/grpc-netty-shaded/jars/grpc-netty-shaded-1.9.0.jar:META-INF/io.netty.versions.properties
///Users/a1353612/.ivy2/cache/io.netty/netty-tcnative-boringssl-static/jars/netty-tcnative-boringssl-static-2.0.0.Final.jar:META-INF/io.netty.versions.properties

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

resolvers += "mvn central" at "http://central.maven.org/maven2/"

assemblyJarName := "nlu-intent-toolkit.jar"