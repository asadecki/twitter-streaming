package albacross

import albacross.config.Configuration
import albacross.math.TwitterAdAnalyzer
import albacross.twitter.TwitterKeywordType
import org.apache.spark.SparkConf

object AnalysisMain {
  def main(args: Array[String]) {
    val config = new Configuration("./src/main/resources/config.properties")

    val sparkConfig = new SparkConf(true)
      .setAppName(config.appName)
      .setMaster(config.master)
      .set("spark.cassandra.connection.host", config.cassandraHostname)
      .set("spark.cassandra.auth.username", config.cassandraUser)
      .set("spark.cassandra.auth.password", config.cassandraPassword)

    val analyzer = new TwitterAdAnalyzer(sparkConfig)

    analyzer.findPolynomial("Nike", TwitterKeywordType.Text, "views")
  }
}
