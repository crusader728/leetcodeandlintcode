package scala.crusader728.leetcode.dfs

import scala.crusader728.leetcode.TreeNode

object MinimumDepthBinaryTree111 {
  def minDepth(root: TreeNode): Int = {
    def dfs(n: TreeNode): Int = {
      n match {
        case x if x.left == null && x.right == null => 1
        case _ => {
          val left = if(n.left == null) Int.MaxValue else dfs(n.left)
          val right = if(n.right == null) Int.MaxValue else dfs(n.right)
          (left, right) match {
            case (Int.MaxValue, Int.MaxValue) => throw new RuntimeException
            case (Int.MaxValue, _) => right + 1
            case (_, Int.MaxValue) => left + 1
            case (_, _) => Math.min(left, right) + 1
          }
        }
      }
    }

    if(root == null) {
      0
    } else {
      dfs(root)
    }
  }
}
