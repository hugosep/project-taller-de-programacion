package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import javax.jms.{Message, MessageListener, Session, TextMessage}
import scala.List

class appDB {

  def procesaMensaje(msj: String): Unit = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()

    val session = connection.createSession(false, Session.AUTO_KNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val consumer = session.createConsumer(queue)

    val listener = new MessageListener {
      def onMessage(message: Message): Unit = {
        message match {
          case text: TextMessage => {
            println(r"Mensaje en cola myQueue: $text\n")
            addData(text)
          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)
  }

  def addData(data: String): Int = {
    try {
      ServicioDB.add(data)
      1
    } catch {
      case _: Exception => -1
    }
  }

  def extraccion(datos: List[String]): List[String] = {

  }
}
