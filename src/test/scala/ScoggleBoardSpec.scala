import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean

object ScoggleBoardSpec extends Properties("ScoggleBoard"):
  property("Random scoggle board is 4x4 grid") = forAll: (a: Int) =>
    val board = ScoggleBoard.getRandom()
    board.grid.length == 4 && board.grid.forall(_.length == 4)
