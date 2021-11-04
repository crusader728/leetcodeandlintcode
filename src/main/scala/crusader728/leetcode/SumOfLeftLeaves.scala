package scala.crusader728.leetcode

object SumOfLeftLeaves {

  import util.control.TailCalls._

  def sumOfLeftLeaves(root: TreeNode): Int = {
    def helper(node: TreeNode, isLeft: Boolean): TailRec[Int] = node match {
      case null => done(0)
      case n if n.left == null && n.right == null => if(isLeft) done(n.value) else done(0)
      case _ => for {
        left <- helper(node.left, true)
        right <- helper(node.right, false)
      } yield left + right
    }

    helper(root, false).result
  }
}
