package albacross.config

import java.io.File

import com.typesafe.config.ConfigFactory

class Configuration(configFile : String) {

  val config = ConfigFactory.parseFile(new File(configFile))

  val batchDuration = config.getInt("batchDuration")
  val companies = config.getString("companies").split(",")
  val appName = config.getString("appName")
  val master = config.getString("master")
  val cassandraHostname = config.getString("cassandraHostname")
  val cassandraUser = config.getString("cassandraUser")
  val cassandraPassword = config.getString("cassandraPassword")
}
