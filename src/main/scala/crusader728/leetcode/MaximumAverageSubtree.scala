package scala.crusader728.leetcode

object MaximumAverageSubtree {

  def maximumAverageSubtree(root: TreeNode): Double = {
    def preorder(root: TreeNode, f: (Int, Int, Int) => Int): LazyList[Int] = root match {
      case n if n == null => LazyList.empty
      case n@_ =>
        val l = preorder(n.left, f)
        val r = preorder(n.right, f)
        f(l.headOption.getOrElse(0), r.headOption.getOrElse(0), root.value) #:: l #::: r
    }

    val sums = preorder(root, (l, r, v) => l + r + v)
    val counts = preorder(root, (l, r, _) => l + r + 1)
    sums
      .zip(counts)
      .map {case (s, c) => s.toDouble / c.toDouble}
      .max
  }
}
