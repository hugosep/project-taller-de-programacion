package taller

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.Session

object appArch {
  val activeMQURL: String = "tcp://localhost:61616"

  def main(args: Array[String]) = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()
    val session = connection.createSession(false, Session.AUTO_KNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val productor = session.createProducer(queue)
    val msgStr = session.createTextMessage("Hola Mundo")
  }

}
