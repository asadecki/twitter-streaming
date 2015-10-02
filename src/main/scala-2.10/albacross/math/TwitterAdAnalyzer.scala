package albacross.math

import albacross.twitter.TwitterKeywordType.TwitterKeywordType
import com.datastax.spark.connector._
import org.apache.spark.{SparkConf, SparkContext}

class TwitterAdAnalyzer(sparkConf: SparkConf) {

  val sparkContext = new SparkContext(sparkConf)

  val KEYSPACE = "reports"
  val TWEETS_TABLE = "tweets_analysis"
  val ADS_TABLE = "aggregation"

  def findPolynomial(keyword: String, keywordType: TwitterKeywordType, analysisKey: String) = {
    val twitterCountPerDay = getTwitterCountPerDay(keyword, keywordType)
    val adCountPerDay = getAdCountPerDay(keyword, analysisKey)

    val nodes = buildNodes(twitterCountPerDay, adCountPerDay)

    nodes.toMap.foreach(println(_))
    val result = PolynomialInterpolation.calculateParameters(nodes)

    result.toArray.reverse.foreach(println(_))
  }

  def buildNodes(twitterCountPerDay: collection.Map[Long, Int], adCountPerDay: collection.Map[Long, Long]): Map[Double,Double] = {
    val keySet = adCountPerDay.keySet
    keySet
      .filter(twitterCountPerDay contains)
      .map(key => (adCountPerDay.get(key), twitterCountPerDay.get(key)))
      .map{case (x,y) => (x.get.toDouble,y.get.toDouble)}
      .toMap[Double, Double]
  }

  def getTwitterCountPerDay(keyword: String, keywordType: TwitterKeywordType) = {
    sparkContext.cassandraTable(KEYSPACE, TWEETS_TABLE)
      .filter(row => row.getString("keyword").equals(keyword) && row.getString("type").equals(keywordType.toString))
      .map(row => (row.getLong("ds_id"), row.getInt("count")))
      .groupBy(_._1)
      .mapValues(_.map(_._2).sum)
      .sortByKey()
      .collectAsMap()
  }

  def getAdCountPerDay(keyword: String, analysisKey: String) = {
    sparkContext.cassandraTable(KEYSPACE, ADS_TABLE)
      .filter(row => row.getString("company_name").equals(keyword))
      .map(row => (row.getLong("ds_id"), row.getLong(analysisKey)))
      .groupBy(_._1)
      .mapValues(_.map(_._2).sum)
      .sortByKey()
      .collectAsMap()
  }
}
