package taller

import org.apache.activemq.ActiveMQConnectionFactory

object objeto {
  val activeMQURL: String = "tcp://localhost:61616"

  def main(args: Array[String]) = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
  }

}
