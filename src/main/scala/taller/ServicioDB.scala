package taller

import java.sql.DriverManager
import java.sql.Connection

object ServicioDB {

  val host: String = ""

  def add(data: String): Boolean = {
    val driver = "com.mysql.jdbc.Driver"
    val url = "jdbc:mysql://localhost:3306/MUSICA"
    val username = "root"
    val password = "1234+"

    var connection: Connection = null
    var songs: List[Song] = List()

    try {
      Class.forName(driver)

      connection = DriverManager.getConnection(url, username, password)

      val statement = connection.createStatement()


      val tempLine: String = data
                        .replace(" ", "")
                        .replace("\n", "")
                        .strip()

      val song = tempLine.split(",")

      val resultSet = statement.executeQuery(s"INSERT INTO CANCIONES(NOMBRE, GENERO, AUTOR) " +
        s"VALUES (${song(0)}, ${song(1)}, ${song(2)})")

      if (!resultSet.next) {
        println("Inserted successfully.\n")
      } else {
        while (resultSet.next) {
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
