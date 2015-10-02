package albacross

import albacross.config.Configuration
import albacross.twitter.TweetsFetcher
import albacross.twitter.auth.TwitterOAuth
import org.apache.spark.SparkConf

object TwitterMain {
  def main(args: Array[String]) {
    TwitterOAuth.setCredentials("./src/main/resources/twitter.properties")
    val config = new Configuration("./src/main/resources/config.properties")

    val sparkConfig = new SparkConf(true)
      .setAppName(config.appName)
      .setMaster(config.master)
      .set("spark.cassandra.connection.host", config.cassandraHostname)
      .set("spark.cassandra.auth.username", config.cassandraUser)
      .set("spark.cassandra.auth.password", config.cassandraPassword)

    val tweetsFetcher = new TweetsFetcher(sparkConfig, config.batchDuration)

    tweetsFetcher.fetchTwitterData(config.batchDuration, config.companies)
  }
}
