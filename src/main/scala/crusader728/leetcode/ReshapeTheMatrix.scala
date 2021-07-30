package scala.crusader728.leetcode

object ReshapeTheMatrix {
  def matrixReshape(nums: Array[Array[Int]], r: Int, c: Int): Array[Array[Int]] = {
    if (nums.length * nums(0).length != r * c) return nums
    nums.flatten.grouped(c).toArray
  }
}
