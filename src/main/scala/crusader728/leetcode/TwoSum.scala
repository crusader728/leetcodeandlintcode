package scala.crusader728.leetcode

object TwoSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val memo = scala.collection.mutable.HashMap.empty[Int, Int]
    @scala.annotation.tailrec
    def loop(i: Int): (Int, Int) = {
      i match {
        case n if n == nums.length => throw new RuntimeException
        case _ => {
          val other = target - nums(i)
          memo.get(other) match {
            case None => {
              memo += nums(i) -> i
              loop(i + 1)
            }
            case Some(j) => (i, j)
          }
        }
      }
    }

    val (i, j) = loop(0)
    val ans = Array.ofDim[Int](2)
    ans(0) = i
    ans(1) = j
    ans
  }
}
