package scala.crusader728.leetcode

object InvertBinaryTree {
  import scala.util.control.TailCalls._

  def invertTree(root: TreeNode): TreeNode = {
    def helper(r: TreeNode): TailRec[TreeNode] = r match {
      case null => done(null)
      case _ => for {
        l <- helper(r.right)
        r <- helper(r.left)
      } yield new TreeNode(r.value, l, r)
    }

    helper(root).result
  }

}
