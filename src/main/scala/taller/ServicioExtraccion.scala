package taller

import org.apache.activemq.ActiveMQConnectionFactory

import java.sql.{Connection, DriverManager}
import javax.jms.{Session, TextMessage}

object ServicioExtraccion {
  val driver = "com.mysql.cj.jdbc.Driver"
  val url = "jdbc:mysql://localhost:3306/MUSICA"
  //val username = "root2"
  val username = "root"
  val password = "1234+"

  def obtenerYEnviar(): Boolean = {
    var connection: Connection = null

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()
      val query = s"SELECT nombre,genero,autor from canciones"
      val resultSet = statement.executeQuery(query)

      val cFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?jms.prefetchPolicy.queuePrefetch=1")
      val connectToQueue = cFactory.createConnection()
      connectToQueue.start()

      val session = connectToQueue.createSession(false, Session.AUTO_ACKNOWLEDGE)
      val queue = session.createQueue("mqDistribuir")

      val productor = session.createProducer(queue)

      while(resultSet.next()) {
        val text2send = s""+ resultSet.getString("nombre")+","+resultSet.getString("genero") +","+resultSet.getString("autor")
        println(text2send)
        productor.send(session.createTextMessage(s""+ resultSet.getString("nombre")+","+resultSet.getString("genero") +","+resultSet.getString("autor")))
      }
      //println(s"Recibido: $txt")

      productor.close()
      session.close()
      connection.close()
      true
    }  catch {
      case e: Exception => {e.printStackTrace(); true}

    }finally{
      connection.close()
    }
  }
}
