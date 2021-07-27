package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.io.{BufferedWriter, File, FileWriter, PrintWriter}
import javax.jms.Session

class appArch {
  val activeMQURL: String = "tcp://localhost:61616"

  def enviarArchivo(): Boolean = {
    return false
  }

  def extraer(pathFile: String): Unit = {
    val lines: List[String] = servicioArchivo.leer(pathFile)
  }

  def main(args: Array[String]): Unit = {
    val cFactory = new ActiveMQConnectionFactory()
    val connection = cFactory.createConnection()
    connection.start()
    val session = connection.createSession(false, Session.AUTO_KNOWLEDGE)
    val queue = session.createQueue("myQueue")

    val productor = session.createProducer(queue)
    val msgStr = session.createTextMessage("Hola Mundo")
    val pathFile = ""
    val file = new File(pathFile)

    val printW = new PrintWriter(file)

    printW.write("")
    printW.close() // las acciones solo se hacen cuando se ejecuta el close

    val buffW = new BufferedWriter(new FileWriter(file))
    buffW.write("")
    buffW.close()


  }

}
