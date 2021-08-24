package scala.crusader728.leetcode

object NthDigit {
  def findNthDigit(n: Int): Int = {
    @scala.annotation.tailrec
    def helper(x: Int, length: Int): Int = {
      val size = Math.pow(10, length - 1).toLong * 9 * length
      if(x.toLong < size) {
        val nth = x / length
        val offset = x % length.toInt
        val number = Math.pow(10, length - 1).toInt + nth
        number.toString.charAt(offset) - '0'
      } else {
        helper((x.toLong - size).toInt, length + 1)
      }
    }

    helper(n - 1, 1)
  }
}
