package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.sql.{Connection, DriverManager}
import javax.jms.{Message, MessageListener, Session, TextMessage}
import scala.io.StdIn.readLine

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
            println(s"Nuevo cambio en BD: $text\n")
          }
          case _ => throw new Exception()
        }
      }
    }
    consumer.setMessageListener(listener)

    while(true) {
      val update = readLine("Imprimir contenido DB? (y/n): ")
      update match {
        case "y" => {
          ServicioExtraccion.queryToDB()
          println("\n")
        }
        case "n" => println("Cerrando host...")
      }
    }
  }
}
