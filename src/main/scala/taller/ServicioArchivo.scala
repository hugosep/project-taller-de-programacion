package taller

import scala.io.Source

object ServicioArchivo {

  def leer(referencia: String): List[Song] = {
    val file = Source.fromFile(referencia)
    // not blocking method
    var data: List[Song] = List[Song]()

    for(line <- file.getLines()) {
      val sliced = line.replace(" ", "")
      val slicedStr = sliced.split(",")
      val song = new Song(slicedStr(0), slicedStr(1), slicedStr(2))
      data = data :+ song
    }
    data
  }
}
