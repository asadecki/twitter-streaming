name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies  ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.5.1",
  "org.apache.spark" % "spark-streaming_2.10" % "1.5.0",
  "org.apache.spark" % "spark-streaming-twitter_2.10" % "1.5.0",
  "org.scalanlp" %% "breeze" % "0.11.2",
  "org.scalanlp" %% "breeze-natives" % "0.11.2",
  "org.scalanlp" %% "breeze-viz" % "0.11.2",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0-M1",
  "com.github.nscala-time" %% "nscala-time" % "2.2.0"
)

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/"
)