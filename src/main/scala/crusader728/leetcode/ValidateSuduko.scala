package scala.crusader728.leetcode

object ValidateSuduko {
  def isValidSudoku(board: Array[Array[Char]]): Boolean = {
    def hasNoDuplication(array: Array[Char]): Boolean = {
      val filtered = array.filter(_ != '.')
      filtered.distinct.length == filtered.length
    }

    def submatrices(board: Array[Array[Char]]): Iterator[Array[Char]] =
      board.grouped(3)
        .map(_.transpose)
        .flatMap(_.grouped(3))
        .map(_.flatten)

    val validateRowResult = board.forall(hasNoDuplication)
    val validateColumnResult = board.transpose.forall(hasNoDuplication)
    val validateSubmatrixResult = submatrices(board).forall(hasNoDuplication)

    validateColumnResult && validateRowResult && validateSubmatrixResult
  }
}
