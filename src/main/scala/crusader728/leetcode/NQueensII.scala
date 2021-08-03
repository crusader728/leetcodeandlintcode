package scala.crusader728.leetcode

object NQueensII {
  private case class Threat(
                     rows: Set[Int],
                     cols: Set[Int],
                     diagonals: Set[Int],
                     antiDiagonals: Set[Int]
                   )

  def isSafe(x: Int,
             y: Int,
             threat: Threat): Boolean = {
    ! threat.rows(x) && ! threat.cols(y) && ! threat.diagonals(x - y) && ! threat.antiDiagonals(x + y)
  }

  def totalNQueens(n: Int): Int = {
    def go(row: Int, threat: Threat): List[List[Int]] = {
      if(row == n) {
        List(Nil)
      } else {
        for {
          col <- (0 until n).toList if isSafe(row, col, threat)
          newThreat = Threat(threat.rows + row,
            threat.cols + col,
            threat.diagonals + (row - col),
            threat.antiDiagonals + (row + col))
          cols <- go(row + 1, newThreat)
        } yield col :: cols
      }
    }

    go(0, Threat(Set(), Set(), Set(), Set())).size
  }
}
