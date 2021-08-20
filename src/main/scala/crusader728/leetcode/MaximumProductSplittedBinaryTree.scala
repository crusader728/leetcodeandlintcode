package scala.crusader728.leetcode

object MaximumProductSplittedBinaryTree {
  def maxProduct(root: TreeNode): Int = {
    val memo = scala.collection.mutable.HashMap.empty[TreeNode, Int]

    def sum(node: TreeNode): Int = {
      if (node == null) 0
      else {
        val r = node.value + sum(node.left) + sum(node.right)
        memo += node -> r
        r
      }
    }

    def split(node: TreeNode, total: Int): Long = {
      if (node == null) 0
      else List(split(node.left, total), split(node.right, total), memo(node).toLong * (total - memo(node))).max
    }
    val totalSum = sum(root)
    (split(root, totalSum) % 1000000007).toInt
  }
}
