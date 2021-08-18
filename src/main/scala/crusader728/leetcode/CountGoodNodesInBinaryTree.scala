package scala.crusader728.leetcode

object CountGoodNodesInBinaryTree {
  def goodNodes(root: TreeNode): Int = {
    @scala.annotation.tailrec
    def helper(n: TreeNode, v: Int, cont: Int => Int): Int = {
      if(n == null) {
        return cont(0)
      } else {
        helper(n.left, Math.max(v, n.value), lcont(n, Math.max(n.value, v), v, cont))
      }
    }

    def lcont(n: TreeNode, m: Int, v: Int, cont: Int => Int): Int => Int = lr => {
      helper(n.right, m, rcont(lr, n, v, cont))
    }

    def rcont(lr: Int, n: TreeNode, v: Int, cont: Int => Int): Int => Int = rr => {
      if(n.value >= v) {
        cont(lr + rr + 1)
      } else {
        cont(lr + rr)
      }
    }

    if(root == null) {
      0
    } else {
      helper(root, root.value, identity)
    }
  }
}
