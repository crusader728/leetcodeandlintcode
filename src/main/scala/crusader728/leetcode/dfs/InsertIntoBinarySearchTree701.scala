package scala.crusader728.leetcode.dfs

import scala.crusader728.leetcode.TreeNode

object InsertIntoBinarySearchTree701 {
    def insertIntoBST(root: TreeNode, `val`: Int): TreeNode = {
      root match {
        case null => new TreeNode(`val`)
        case _ => if(root.value < `val`) {
          root.right = insertIntoBST(root.right, `val`)
          root
        } else if(root.value > `val`) {
          root.left = insertIntoBST(root.left, `val`)
          root
        } else {
          throw new RuntimeException
        }
      }
    }
}
