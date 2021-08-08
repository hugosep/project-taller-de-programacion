package taller

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.{Message, MessageListener, Session, TextMessage}

class host {

  def getDataYProcesar(nombreCola: String): Unit = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()

    val session = connection.createSession(false, Session.AUTO_KNOWLEDGE)
    val queue = session.createQueue(nombreCola)

    val consumer = session.createConsumer(queue)

    println("Host iniciado...\n")

    val listener = new MessageListener {
      def onMessage(message: Message): Unit = {
        message match {
          case text: TextMessage => {
            println(s"Nuevo cambio en BD: $text\n")
          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)
  }
}
