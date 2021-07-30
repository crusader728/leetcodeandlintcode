package scala.crusader728.leetcode

object LongestIncreasingSubsequence {
  def lengthOfLIS(nums: Array[Int]): Int = {
    val memo = scala.collection.mutable.HashMap.empty[Int, Int]
    def go(i: Int): Int = {
      memo.get(i) match {
        case Some(v) => v
        case None => {
          val result = i match {
            case x if x == nums.length - 1 => 1
            case _ => (i + 1 until nums.length).foldLeft(1) {case (max, j) => {
              if(nums(j) > nums(i)) {
                Math.max(max, 1 + go(j))
              } else {
                max
              }
            }}
          }
          memo += i -> result
          result
        }
      }
    }
    (0 until nums.length).map(i => go(i)).max
  }
}
