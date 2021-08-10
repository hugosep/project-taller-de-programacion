package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.sql.{Connection, DriverManager}
import javax.jms.{Session, TextMessage}

object ServicioExtraccion {
  val driver = "com.mysql.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/MUSICA"
  val username = "root"
  val password = "1234+"

  def obtenerYEnviar(data: String): String = {
    var connection: Connection = null

    try {
      val cFactory = new ActiveMQConnectionFactory()
      val connectToQueue = cFactory.createConnection()
      connectToQueue.start()

      val session = connectToQueue.createSession(false, Session.AUTO_ACKNOWLEDGE)
      val queue = session.createQueue("mqDistribuir")

      val productor = session.createProducer(queue)

      val newSong = session.createTextMessage("")

      productor.send(newSong)

      productor.close()
      session.close()
      connection.close()
      "done"
    } catch {
      case e: Throwable => {
        e.printStackTrace()
        "problem"
      }
    }
  }

  def queryToDB(): Unit = {
    var connection: Connection = null

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()

      val resultSet = statement.executeQuery("SELECT * FROM CANCIONES")

      if (!resultSet.next) {
        println("Inserted successfully.\n")
        val name: String = resultSet.getString("nombre")
        val genre: String = resultSet.getString("genero")
        val author: String = resultSet.getString("autor")
        songs = songs :+ new Song(name, genre, author)
      } else {
        println(s"Problem: $resultSet\n")
      }

      connection.close()

    } catch {
      case e: Throwable => {
        e.printStackTrace()
      }
    }
  }
}
