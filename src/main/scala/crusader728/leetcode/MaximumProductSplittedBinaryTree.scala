package scala.crusader728.leetcode

object MaximumProductSplittedBinaryTree {
  def maxProduct(root: TreeNode): Int = {
    val memo = scala.collection.mutable.HashMap.empty[TreeNode, Int]

    def collectSum(t: TreeNode): Int = {
      t match {
        case null => 0
        case _ => memo.getOrElseUpdate(t, t.value + collectSum(t.left) + collectSum(t.right))
      }
    }

    def split(t: TreeNode, total: Int): Long = {
      t match {
        case null => 0
        case n if n.left == null && n.right == null => 0
        case n if n.left == null => List(split(n.right, total), (total - memo(n.right)).toLong * memo(n.right).toLong).max
        case n if n.right == null => List(split(n.left, total), (total - memo(n.left)).toLong * memo(n.left).toLong).max
        case n@_ => List(split(n.right, total),
          split(n.left, total),
          (total - memo(n.right)).toLong * memo(n.right).toLong,
          (total - memo(n.left)).toLong * memo(n.left).toLong).max
      }
    }

    (split(root, collectSum(root)) % 1000000007).toInt
  }
}
