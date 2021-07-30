package scala.crusader728.leetcode

object GrayCode {
  def grayCode(n: Int): List[Int] = {
    n match {
      case 1 => List(0, 1)
      case _ => {
        val prev = grayCode(n - 1)
        val base = Math.pow(2, n - 1).toInt
        prev ++ prev.reverse.map(i => i + base)
      }
    }
  }
}
