package albacross.twitter.auth

import java.io.File

import com.typesafe.config.ConfigFactory

object TwitterOAuth {

  def setCredentials(configFile: String) = {

    val config = ConfigFactory.parseFile(new File(configFile))

    System.setProperty("twitter4j.oauth.consumerKey", config.getString("consumerKey"))
    System.setProperty("twitter4j.oauth.consumerSecret", config.getString("consumerSecret"))
    System.setProperty("twitter4j.oauth.accessToken", config.getString("accessToken"))
    System.setProperty("twitter4j.oauth.accessTokenSecret", config.getString("accessTokenSecret"))
  }
}
