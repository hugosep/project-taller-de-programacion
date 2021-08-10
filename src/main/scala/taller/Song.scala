package taller

class Song (
             val name: String,
             val genre: String,
             val author: String
           ) {
  def getName: String = this.name
  def getGenre: String = this.genre
  def getAuthor: String = this.author

  override def toString: String = s"($name, $genre, $author)"
}
