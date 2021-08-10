package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import javax.jms.{Session, TextMessage}

class appArch {
  val activeMQURL: String = "tcp://localhost:61616"
  val archivo: String = "data.txt"
  var songs: List[String] = _

  def extraer(archivo: String): Unit = {
    songs = ServicioArchivo.leer(archivo)
  }

  def enviarArchivo(data: List[String]): Boolean = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()
    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val queue = session.createQueue("mqRegistro")

    val productor = session.createProducer(queue)

    try {
      extraer(archivo)

      for(song <- songs) {
        val msg = session.createTextMessage(song)
        productor.send(msg)
      }

      productor.close()
      true
    } catch {
      case e: Exception => {
        println(e.getMessage)
        false
      }
    }
  }

}
