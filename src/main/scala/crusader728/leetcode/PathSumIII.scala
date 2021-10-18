package scala.crusader728.leetcode


object PathSumIII {
  def pathSum(root: TreeNode, targetSum: Int): Int = {
    def pathSumHelper(root: TreeNode, target: Int, start: Boolean): Int = {
      root match {
        case n if n == null => 0
        case n if n.left == null && n.right == null && n.value == target => 1
        case n if n.left == null && n.right == null && n.value != target => 0
        case n@_ => if(!start) {
          val r1 = pathSumHelper(n.left, target, start = false)
          val r2 = pathSumHelper(n.right, target, start = false)
          val r3 = pathSumHelper(n, target, start = true)
          r1 + r2 + r3
        } else {
          val r1 = pathSumHelper(n.left, target - n.value, start = true)
          val r2 = pathSumHelper(n.right, target - n.value, start = true)
          val r3 = if(n.value == target) 1 else 0
          r1 + r2 + r3
        }
      }
    }

    pathSumHelper(root, targetSum, start = false)
  }
}
