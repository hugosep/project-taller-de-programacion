package taller

import scala.io.Source

object ServicioArchivo {

  def leer(referencia: String): List[String] = {
    val file = Source.fromFile(referencia)
    // not blocking method
    var data: List[String] = List()

    for(line <- file.getLines()) {
      data = data :+ line
    }
    data
  }
}
