import scala.util.Random

case class ScoggleBoard private (grid: Array[Array[String]]):
  override def toString(): String =
    grid.map(_.mkString("\t")).mkString("\n")

object ScoggleBoard:
  val SIZE = 4

  private val boardDie = Array(
    Array("A", "A", "E", "E", "G", "N"),
    Array("A", "B", "B", "J", "O", "O"),
    Array("A", "C", "H", "O", "P", "S"),
    Array("A", "F", "F", "K", "P", "S"),
    Array("A", "O", "O", "T", "T", "W"),
    Array("C", "I", "M", "O", "T", "U"),
    Array("D", "E", "I", "L", "R", "X"),
    Array("D", "E", "L", "R", "V", "Y"),
    Array("D", "I", "S", "T", "T", "Y"),
    Array("E", "E", "G", "H", "N", "W"),
    Array("E", "E", "I", "N", "S", "U"),
    Array("E", "H", "R", "T", "V", "W"),
    Array("E", "I", "O", "S", "S", "T"),
    Array("E", "L", "R", "T", "T", "Y"),
    Array("H", "I", "M", "N", "QU", "U"),
    Array("H", "L", "N", "N", "R", "Z")
  )

  def getRandom(): ScoggleBoard =
    val randomizedDiceGrid =
      Random
        .shuffle(boardDie)
        .toArray
        .map(_.apply(Random.nextInt(6)))
        .grouped(SIZE)
        .toArray
    ScoggleBoard(randomizedDiceGrid)
