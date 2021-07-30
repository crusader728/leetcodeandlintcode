package scala.crusader728.leetcode

object LCAInBST {
  def lowestCommonAncestor(root: TreeNode, p: TreeNode, q: TreeNode): TreeNode = {
    if(root.value > p.value) {
      if(root.value <= q.value) {
        root
      } else {
        lowestCommonAncestor(root.left, p, q)
      }
    } else if(root.value < p.value) {
      if(root.value >= q.value) {
        root
      } else {
        lowestCommonAncestor(root.right, p, q)
      }
    } else {
      root
    }
  }
}
