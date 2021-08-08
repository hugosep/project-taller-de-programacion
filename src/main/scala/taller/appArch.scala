package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import javax.jms.Session

class appArch {
  val activeMQURL: String = "tcp://localhost:61616"
  val archivo: String = "data.txt"
  var lines: List[String] = _

  def enviarArchivo(data: List[String]): Boolean = {

  }

  def extraer(archivo: String): Unit = {
    lines = ServicioArchivo.leer(archivo)
  }

  def enviarArchivo(data: List[String]): Boolean = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()
    val session = connection.createSession(false, Session.AUTO_KNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val productor = session.createProducer(queue)
    val msgStr = session.createTextMessage("Hola Mundo")
    val file = new File(archivo)

    try {
      val printW = new PrintWriter(file)

      printW.write("")
      printW.close() // las acciones solo se hacen cuando se ejecuta el close

      val buffW = new BufferedWriter(new FileWriter(file))
      extraer(archivo)

      buffW.write(lines.mkString("\n"))
      buffW.close()

      true

    } catch {
      case e: Exception => {
        println(e.getMessage)
        false
      }
    }
  }

}
