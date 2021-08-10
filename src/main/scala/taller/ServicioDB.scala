package taller

import java.sql.DriverManager
import java.sql.Connection

object ServicioDB {

  val host: String = ""

  def add(data: Song): Int = {
    val driver = "com.mysql.cj.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/MUSICA"
    //val username = "root2"
    val username = "root"
    val password = "1234+"

    var connection: Connection = null

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()
      val query = s"INSERT INTO CANCIONES (nombre,genero,autor)" +
        s" VALUES ('${data.name}', '${data.genre}', '${data.author}')"
      println(query)
      val resultSet = statement.executeUpdate(query)

      if (resultSet == 1)
        println("Inserted successfully.\n")
      else {
        println("Hubo un probleman")
      }

      connection.close()
      1

    } catch {
      case e: Throwable => {
        e.printStackTrace()
        0
      }
    }finally {
      connection.close()
    }
  }
}
