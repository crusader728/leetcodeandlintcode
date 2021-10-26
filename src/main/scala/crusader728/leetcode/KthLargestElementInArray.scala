package scala.crusader728.leetcode



object KthLargestElementInArray {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    nums.sorted(implicitly[Ordering[Int]].reverse)(k - 1)
  }
}
