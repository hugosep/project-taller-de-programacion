package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.sql.{Connection, DriverManager}
import javax.jms.{Session, TextMessage}

object ServicioExtraccion {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/MUSICA"
  val username = "root"
  val password = "1234+"

  def obtenerYEnviar(data: String): List[String] = {
    var connection: Connection = null

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery("SELECT id, nombre, FROM categoria")

      while(resultSet.next()) {
        val id = resultSet.getString("id")
        val nombre = resultSet.getString("nombre")
        println(s"id, nombre: $id, $nombre", id, nombre)
      }
      connection.close()

      val cFactory = new ActiveMQConnectionFactory()
      val connectToQueue = cFactory.createConnection()
      connectToQueue.start()

      val session = connectToQueue.createSession(false, Session.AUTO_KNOWLEDGE)
      val queue = session.createQueue("mqDistribuir")

      val productor = session.createProducer(queue)

      println(s"Recibido: $txt")

      productor.close()
      session.close()
      connection.close()
    } catch {
      case e: Throwable => {
        e.printStackTrace()
        false
      }
    }
  }
}
