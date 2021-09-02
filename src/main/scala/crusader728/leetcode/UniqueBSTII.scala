package scala.crusader728.leetcode

object UniqueBSTII {
  def generateTrees(n: Int): List[TreeNode] = {
    def helper(l: Int, r: Int): List[TreeNode] = {
      if(l >= r) {
        List(null)
      } else if(l == r - 1) {
        List(new TreeNode(l))
      } else {
        for {
          root <- (l until r).toList
          left <- helper(l, root)
          right <- helper(root + 1, r)
        } yield new TreeNode(root, left, right)
      }
    }

    helper(1, n + 1)
  }
}
