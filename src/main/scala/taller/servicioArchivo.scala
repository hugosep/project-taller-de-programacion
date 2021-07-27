package taller

import scala.io.Source

object servicioArchivo {

  def leer(pathFile: String): List[String] = {
    val file = Source.fromFile(pathFile)

    // not blocking method
    /*for(line <- file.getLines()) {
      println(line)
    }*/

    val lines: List[String] = file.getLines().toList

    lines
  }


}
