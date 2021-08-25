package scala.crusader728.leetcode

class SumOfSquareNumbers {

  private def isSquareNumber(i: Int): Boolean = {
    val root = Math.sqrt(i).floor.toInt
    root * root == i
  }

  def judgeSquareSum(c: Int): Boolean = {
    val max = Math.sqrt(c).floor.toInt
    (0 to max).exists(i => {
      isSquareNumber(c - i * i)
    })
  }
}
