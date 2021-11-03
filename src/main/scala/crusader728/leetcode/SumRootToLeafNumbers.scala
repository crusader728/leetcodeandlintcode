package scala.crusader728.leetcode


object SumRootToLeafNumbers {
  import scala.util.control.TailCalls._

  def sumNumbers(root: TreeNode): Int = {
    def go(root: TreeNode, base: Int): TailRec[Int] = root match {
      case null => done(0)
      case n if n.left == null && n.right == null => done(base * 10 + n.value)
      case _ =>
        val newBase = base * 10 + root.value
        for {
          left <- go(root.left, newBase)
          right <- go(root.right, newBase)
        } yield left + right
    }

    go(root, 0).result
  }
}
