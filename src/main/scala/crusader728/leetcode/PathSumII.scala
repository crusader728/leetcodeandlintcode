package scala.crusader728.leetcode


object PathSumII {
  import scala.util.control.TailCalls._

  type Sol = List[Int]
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    helper(root, targetSum, Nil, Nil).result
  }

  def helper(root: TreeNode, target: Int, path: List[Int], acc: List[Sol]): TailRec[List[Sol]] = root match {
    case n if n == null => done(acc)
    case n if n.left == null && n.right == null => if(n.value == target) {
      done((n.value :: path).reverse :: acc)
    }else {
      done(acc)
    }
    case n@_ => for {
      accleft <- helper(n.left, target - n.value, n.value :: path, acc)
      r <- helper(n.right, target - n.value, n.value :: path, accleft)
    } yield r
  }
}
