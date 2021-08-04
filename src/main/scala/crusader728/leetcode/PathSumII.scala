package scala.crusader728.leetcode

object PathSumII {
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    @scala.annotation.tailrec
    def helper(root: TreeNode, target: Int, cont: List[List[Int]] => List[List[Int]]): List[List[Int]] = {
      root match {
        case null => cont(Nil)
        case leaf if leaf.left == null && leaf.right == null => if(leaf.value == target) {
          cont(List(List(leaf.value)))
        } else {
          cont(Nil)
        }
        case _ => helper(root.left, target - root.value, lcont(root.right, target, root.value, cont))
      }
    }

    def lcont(
               right: TreeNode,
               target: Int,
               value: Int,
               k: List[List[Int]] => List[List[Int]]
             ): List[List[Int]] => List[List[Int]] = lr => {
      helper(right, target - value, rcont(lr, value, k))
    }

    def rcont(
               lr: List[List[Int]],
               value: Int,
               k: List[List[Int]] => List[List[Int]]
             ): List[List[Int]] => List[List[Int]] = rr => {
      k((lr ++ rr).map(l => value :: l))
    }

    helper(root, targetSum, identity)
  }
}
