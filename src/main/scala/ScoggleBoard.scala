import scala.util.Random

/** A case class representing a scoggle board
  *
  * @param grid
  *   2D array of strings representing the scoggle board
  */
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

  /** Create a scoggle board from a 2D array of strings
    *
    * @param grid
    *   The 2D array of strings representing the scoggle board
    * @return
    *   Either an error message or a valid scoggle board
    */
  def fromArray(grid: Array[Array[String]]): Either[String, ScoggleBoard] =
    if grid.length == SIZE && grid.forall(_.length == SIZE) then Right(ScoggleBoard(grid))
    else Left("Invalid scoggle grid supplied")

  /** Generate a random scoggle board
    *
    * @return
    *   A randomized scoggle board
    */
  def getRandom(): ScoggleBoard =
    val randomizedDiceGrid =
      Random
        .shuffle(boardDie)
        .toArray
        .map(_.apply(Random.nextInt(6)))
        .grouped(SIZE)
        .toArray
    ScoggleBoard(randomizedDiceGrid)
