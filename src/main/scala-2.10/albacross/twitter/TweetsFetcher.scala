package albacross.twitter

import java.text.SimpleDateFormat

import com.datastax.spark.connector._
import com.github.nscala_time.time.Imports._
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Duration, Minutes, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import twitter4j.Status

class TweetsFetcher(sparkConf: SparkConf, batchDuration: Int) extends Serializable {

  val sparkContext = new SparkContext(sparkConf)
  val streamingContext = new StreamingContext(sparkContext, Minutes(batchDuration))

  val formatter = new SimpleDateFormat("yyyyMMdd")

  val TWEETS_KEYSPACE = "reports"
  val TWEETS_TABLE = "tweets_analysis"
  val TWEETS_COLUMNS = SomeColumns("keyword", "ds_id", "type", "start_date", "end_date", "count")

  def fetchTwitterData(timePeriod: Int, filters: Array[String]) = {

    val twitterStream = TwitterUtils.createStream(streamingContext, None, filters)

    val groupedByKeyword = createStream(Minutes(timePeriod), twitterStream, filters)
    val groupedByHashTag = createStream(Minutes(timePeriod), twitterStream, filters map ("#" + _))

    groupedByKeyword.foreachRDD(rdd => {
      val (today, now, someTimeLater) = getDateAndTime
      rdd.map { case (name, count) => (name, today, TwitterKeywordType.Text, now, someTimeLater, count) }
        .saveToCassandra(TWEETS_KEYSPACE, TWEETS_TABLE, TWEETS_COLUMNS)
    })

    groupedByHashTag.foreachRDD(rdd => {
      val (today, now, someTimeLater) = getDateAndTime
      rdd.map { case (name, count) => (name stripPrefix "#", today, TwitterKeywordType.Hashtag, now, someTimeLater, count) }
        .saveToCassandra(TWEETS_KEYSPACE, TWEETS_TABLE, TWEETS_COLUMNS)
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }

  def createStream(timePeriod: Duration, stream: ReceiverInputDStream[Status], filters: Array[String]): DStream[(String, Int)] = {
    stream
      .map(_.getText.split(" "))
      .flatMap(splittedLine => filters intersect splittedLine)
      .map((_, 1))
      .reduceByKeyAndWindow(_ + _, timePeriod)
  }

  def getDateAndTime = {
    val today = LocalDate.now()
    val now = DateTime.now()
    val someTimeLater = now.plusMinutes(batchDuration)
    (today.toString("yyyyMMdd"), now, someTimeLater)
  }
}

object TwitterKeywordType extends Enumeration {
  type TwitterKeywordType = Value
  val Text, Hashtag = Value
}
