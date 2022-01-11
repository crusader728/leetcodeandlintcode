package scala.crusader728.leetcode.array

object IfEveryRowColumnContainsAllNumbers2133 {

  def checkRow(matrix: Array[Array[Int]], n: Int, i: Int): Boolean = {
    matrix(i).toSet.size == n
  }
  def checkColumn(matrix: Array[Array[Int]], n: Int, i: Int): Boolean = {
    (0 until n).foldLeft(Set.empty[Int]) { case (acc, j) => acc + matrix(j)(i) }.size == n
  }

  def checkValid(matrix: Array[Array[Int]]): Boolean = {
    val n = matrix.length
    (0 until n).forall(i => checkRow(matrix, n, i) && checkColumn(matrix, n, i))
  }
}
