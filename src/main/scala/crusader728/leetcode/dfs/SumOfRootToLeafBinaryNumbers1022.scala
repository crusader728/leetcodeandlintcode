package scala.crusader728.leetcode.dfs

import scala.crusader728.leetcode.TreeNode

object SumOfRootToLeafBinaryNumbers1022 {
  def sumRootToLeaf(root: TreeNode): Int = {
    dfs(root, 0)
  }

  def dfs(n: TreeNode, base: Int): Int = {
    n match {
      case null => base
      case x if x.left == null && x.right == null => base * 2 + x.value
      case _ => {
        val nextBase = base * 2 + n.value
        val left = if(n.left != null) {
          dfs(n.left, nextBase)
        } else {
          0
        }
        val right = if(n.right != null) {
          dfs(n.right, nextBase)
        } else {
          0
        }
        left + right
      }
    }
  }
}
