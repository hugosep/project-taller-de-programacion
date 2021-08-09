package taller

import org.apache.activemq.ActiveMQConnectionFactory

import javax.jms.{Message, MessageListener, Session, TextMessage}

class host {

  def getDataYProcesar(nombreCola: String): Unit = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()

    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val queue = session.createQueue(nombreCola)

    val consumer = session.createConsumer(queue)

    println("Host iniciado...\n")

    val listener = new MessageListener {
      def onMessage(message: Message): Unit = {
        message match {
          case text: TextMessage => {
            val slicedStr = text.getText.split(",")
            println("sEl mensaje que llego fue nobmre: "+slicedStr(0)+ " genero: "+ slicedStr(1)+ " autor: "+slicedStr(2))
          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)
  }
}

object Host{
  def main(args: Array[String]): Unit= {
    val hostjob = new host
    hostjob.getDataYProcesar("mqDistribuir")
  }
}
