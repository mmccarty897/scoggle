import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean
import org.scalacheck.Gen
import org.scalacheck.Prop

object ScoggleBoardSpec extends Properties("ScoggleBoard"):
  private def buildGrid(size: Int): Array[Array[String]] =
    val grid = Array.ofDim[String](size, size)
    for
      i <- 0 until size
      j <- 0 until size
    do grid(i)(j) = "A"
    grid

  val smallishInts = Gen.choose(5, 1_000)

  property("getRandom: Random scoggle board is 4x4 grid") = forAll: (a: Int) =>
    val board = ScoggleBoard.getRandom()
    board.grid.length == 4 && board.grid.forall(_.length == 4)

  property("fromArray: Provided 4x4 letter grid returns a valid scoggle board") = forAll:
    (a: Int) =>
      val grid = buildGrid(4)
      ScoggleBoard.fromArray(grid).isRight

  property("fromArray: Provided grid larger than 4x4 returns Left") = Prop.forAll(smallishInts):
    n =>
      val grid = buildGrid(n)
      ScoggleBoard.fromArray(grid).isLeft
