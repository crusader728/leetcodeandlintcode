package scala.crusader728.leetcode

object BinaryTreePruning {
  def pruneTree(root: TreeNode): TreeNode = {
    root match {
      case null => null
      case n if n.left == null && n.right == null && n.value == 0 => null
      case n if n.left == null && n.right == null => n
      case _ => {
        val left = pruneTree(root.left)
        val right = pruneTree(root.right)
        if(left == null && right == null && root.value == 0) {
          null
        } else {
          root.left = left
          root.right = right
          root
        }
      }
    }
  }
}
