package taller

import java.sql.DriverManager
import java.sql.Connection

object servicioDB {

  val host: String = ""

  def data(data: String): Boolean = {
    false
  }

  def main(args: Array[String]): Unit = {
    val driver = ""
    val url = "jdbc:mysql://localhost:3306/"
    val username = ""
    val password = ""

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
    } catch {
      case e: Throwable => e.printStackTrace()
    }

    connection.close()
  }
}
