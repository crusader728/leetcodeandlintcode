package scala.crusader728.leetcode


object CousinsInBinaryTree {
  import scala.annotation.tailrec
  def isCousins(root: TreeNode, x: Int, y: Int): Boolean = {
    val nodesInfo = nodes(root)
    val xInfo = nodesInfo.filter(info => info.self.value == x).head
    val yInfo = nodesInfo.filter(info => info.self.value == y).head

    xInfo.level == yInfo.level && xInfo.parent != yInfo.parent
  }

  case class Info(parent: TreeNode, level: Int, self: TreeNode)

  def nodes(root: TreeNode): LazyList[Info] = {
    @tailrec
    def bfs(q: LazyList[Info], acc: LazyList[Info]): LazyList[Info] = {
      if(q.isEmpty) {
        acc
      } else {
        val nextLevel = for {
          i <- q
          children = List(i.self.left, i.self.right).filter(n => n != null)
          child <- children
          info = Info(i.self, i.level + 1, child)
        } yield info
        bfs(nextLevel, acc #::: q)
      }
    }

    bfs(LazyList(Info(null, 0, root)), LazyList.empty)
  }
}
