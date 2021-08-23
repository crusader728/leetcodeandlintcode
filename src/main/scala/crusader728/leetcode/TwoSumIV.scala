package scala.crusader728.leetcode

object TwoSumIV {
  private def stream(root: TreeNode): LazyList[TreeNode] = {
    case null => LazyList.empty
    case _ => stream(root.left) #::: (root #:: stream(root.right))
  }


  def findTarget(root: TreeNode, k: Int): Boolean = {
    val seen = scala.collection.mutable.HashSet.empty[Int]
    val values = stream(root).map(_.value)
    @scala.annotation.tailrec
    def loop(ll: LazyList[Int]): Boolean = {
      if(ll.isEmpty) {
        false
      } else {
        val a = ll.head
        if(seen.contains(k - a)) {
          true
        } else {
          seen += a
          loop(ll.tail)
        }
      }
    }

    loop(values)
  }
}
