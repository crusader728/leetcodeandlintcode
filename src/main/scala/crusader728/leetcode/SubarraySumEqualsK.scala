package scala.crusader728.leetcode

object SubarraySumEqualsK {
  def subarraySum(nums: Array[Int], k: Int): Int = {
    val prefixs = nums.scan(0)(_ + _)
    val m = collection.mutable.HashMap.empty[Int, Int]
    prefixs.foldLeft(0) {case (acc, sum) => {
      val result = acc + m.getOrElse(sum - k, 0)
      m += sum -> (m.getOrElse(sum, 0) + 1)
      result
    }}
  }

  def main(args: Array[String]): Unit = {
    Array(1,2,3).scan(0)(_ + _).foreach{println}
  }
}
