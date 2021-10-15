package scala.crusader728.leetcode


object JumpGame {
  def canJump(nums: Array[Int]): Boolean = {
    nums.indices.foldRight(nums.length - 1) {case (i, pos) =>
      if(i + nums(i) >= pos) {
        i
      } else pos
    } == 0
  }
}
