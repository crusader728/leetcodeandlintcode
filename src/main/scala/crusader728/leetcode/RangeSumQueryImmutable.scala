package scala.crusader728.leetcode

object RangeSumQueryImmutable {
  class NumArray(_nums: Array[Int]) {
    private val prefix: Array[Int] = _nums.scanLeft(0)(_+_)

    def sumRange(left: Int, right: Int): Int = {
      prefix(right + 1) - prefix(left)
    }

  }
}
