package scala.crusader728.leetcode

object SortedArrayToBST {
  def sortedArrayToBST(nums: Array[Int]): TreeNode = {
    def helper(l: Int, r: Int): TreeNode = {
      if(l > r) {
        null
      } else {
        val mid = l + (r - l) / 2
        new TreeNode(nums(mid), helper(l, mid - 1), helper(mid + 1, r))
      }

    }

    helper(0, nums.size - 1)
  }
}
