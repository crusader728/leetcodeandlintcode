package scala.crusader728.leetcode

object ValidateSuduko {
  type Row = Int
  type Col = Int
  type Group = Int
  type Cord = (Int, Int)

  val getRow: Cord => Row = cord => cord._1
  val getCol: Cord => Col = cord => cord._2

  case class Cell(cord: Cord, value: Option[Int])
  type Board = Vector[Cell]

  implicit class ToBoardOps(val array: Array[Array[Char]]) extends AnyVal {
    def toBoard: Board = {
      (for {
        i <- array.indices
        j <- array(i).indices
      } yield (i, j))
        .map(cord => {
          val row = getRow(cord)
          val col = getCol(cord)
          val value = array(row)(col)
          if(value == '.') {
            Cell(cord, None)
          } else {

            Cell(cord, Some(value.toInt - '0'.toInt))
          }
        }).toVector
    }
  }

  def validateRow(board: Board, r: Row): Boolean = {
    (for {
      i <- 0 until 9
    } yield r * 9 + i)
      .map(i => board(i))
      .map(cell => cell.value)
      .filter(_.isDefined)
      .map(_.get)
      .groupBy(identity)
      .forall(t => t._2.size == 1)
  }

  def rowValidateMachine(board: Board): Boolean =
    (0 until 9).map(r => validateRow(board, r)).forall(identity)

  def validateCol(board: Board, col: Col): Boolean = {
    (0 until 9)
      .map(row => row * 9 + col)
      .map(board(_))
      .map(_.value)
      .filter(_.isDefined)
      .map(_.get)
      .groupBy(identity)
      .forall(t => t._2.size == 1)
  }

  def colValidateMachine(board: Board): Boolean =
    (0 until 9).map(col => validateCol(board, col)).forall(identity)

  def validateGroup(board: Board, group: Group): Boolean = {
    (for {
      i <- 0 until 3
      j <- 0 until 3
    } yield ((group / 3) * 3 + i) * 9 + (group % 3) * 3 + j)
      .map(board)
      .map(_.value)
      .filter(_.isDefined)
      .map(_.get)
      .groupBy(identity)
      .forall(t => t._2.size == 1)

  }

  def groupValidateMachine(board: Board): Boolean = {
    (0 until 9).map(grp => validateGroup(board, grp)).forall(identity)
  }

  def validateBoard(board: Board): Boolean = {
    rowValidateMachine(board) && colValidateMachine(board) && groupValidateMachine(board)
  }

  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    val bd = board.toBoard
    validateBoard(bd)
  }
}
