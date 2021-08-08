package taller

import scala.io.Source

object ServicioArchivo {

  def leer(referencia: String): List[Song] = {
    val file = Source.fromFile(referencia)
    // not blocking method
    var data: List[Song] = List()

    for(line <- file.getLines()) {
      var sliced = line.replace(" ", "")
      val slicedStr = sliced.split(",")
      data += new Song(slicedStr(0), slicedStr(1), slicedStr(2))
    }
    data
  }


}
