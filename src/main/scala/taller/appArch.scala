package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import javax.jms.Session

class appArch {
  val activeMQURL: String = "tcp://localhost:61616"
  var lines: List[String] = _
  val archivo = ""

  def extraer(archivo: String): List[String] = {
    ServicioArchivo.leer(archivo)
  }

  def enviarArchivo(data: List[String]): Boolean = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()
    val session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val productor = session.createProducer(queue)
    for (line <- data) {
      productor.send(session.createTextMessage(line))
    }
    true
  }


}


object MainArch{
  def main(args: Array[String]): Unit= {
    val archivo = new appArch
    if(archivo.enviarArchivo(archivo.extraer("data.txt"))){
      println("Todo encolado")
    }
  }
}
