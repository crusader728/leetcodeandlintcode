package scala.crusader728.leetcode.bfs

import scala.crusader728.leetcode.TreeNode

object BinaryTreeRightSideView199 {
  def rightSideView(root: TreeNode): List[Int] = {
    if(root == null) {
      Nil
    } else {
      val m = collection.mutable.Map.empty[Int, List[TreeNode]]
      @annotation.tailrec
      def bfs(level: List[TreeNode], depth: Int): Unit = {
        if(level.isEmpty) {
          ()
        } else {
          m += (depth -> level)
          val next = for {
            n <- level
            children <- List(n.right, n.left).filter(node => node != null)
          } yield children
          bfs(next, depth + 1)
        }
      }

      bfs(List(root), 0)
      (0 to m.keySet.max).map(h => m(h).head.value).toList
    }
  }
}
