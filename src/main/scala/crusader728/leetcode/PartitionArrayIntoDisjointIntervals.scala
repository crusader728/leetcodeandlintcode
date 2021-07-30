package scala.crusader728.leetcode

object PartitionArrayIntoDisjointIntervals {
  def partitionDisjoint(nums: Array[Int]): Int = {
    val maxLeft = nums.tail.scanLeft(nums.head) {case (m, i) => {
      Math.max(m, i)
    }}
    val minRight = nums.init.scanRight(nums.last) {case (i, m) => {
      Math.min(m, i)
    }}

    @scala.annotation.tailrec
    def loop(i: Int): Int = {
      i match {
        case n if n == nums.length - 1 => throw new RuntimeException
        case _ => if(maxLeft(i) <= minRight(i + 1)) {
          i
        } else {
          loop(i + 1)
        }
      }
    }

    loop(0) + 1
  }
}
