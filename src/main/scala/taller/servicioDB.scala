package taller

import java.sql.DriverManager
import java.sql.Connection

object servicioDB {

  val host: String = ""

  def add(data: Map[String, String]): Boolean = {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/MUSICA"
    val username = "root"
    val password = "1234+"

    var connection: Connection = null

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()
      val resultSet = statement.executeQuery(s"INSERT INTO CANCIONES(NOMBRE, GENERO, AUTOR) " +
        s"VALUES (${data["nombre"]}, ${data["genero"]}, ${data["autor"]})")

      if (!resultSet.next)
        println("Inserted successfully.\n")
      else {
        while(resultSet.next){
          println(s"Problem: $resultSet\n")
        }
      }

      connection.close()
      true

    } catch {
      case e: Throwable => {
        e.printStackTrace()
        false
      }
    }
  }
}
