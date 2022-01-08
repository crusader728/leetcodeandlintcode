package scala.crusader728.leetcode.tree

import scala.collection.SortedMap
import scala.crusader728.leetcode.TreeNode

object BinaryTreeVerticalOrder314 {
  type Offset = Int
  type Info = (TreeNode, Offset)
  val getOffset: Info => Offset = _._2
  val getNode: Info => TreeNode = _._1


  def verticalOrder(root: TreeNode): List[List[Int]] = {
    if(root == null) {
      Nil
    } else {
      val q = collection.mutable.Queue.empty[Info]
      q.enqueue((root, 0))
      @scala.annotation.tailrec
      def bfs(m: SortedMap[Offset, List[Int]]): SortedMap[Offset, List[Int]] = {
        if(q.isEmpty) {
          m
        } else {
          val info = q.dequeue()
          val offset = getOffset(info)
          val node = getNode(info)
          if(node.left != null) {
            q.enqueue((node.left, offset - 1))
          }
          if(node.right != null) {
            q.enqueue((node.right, offset + 1))
          }
          val newM = m + (offset -> (node.value :: m.getOrElse(offset, List.empty)))
          bfs(newM)
        }
      }
      bfs(SortedMap.empty)
        .values
        .map(_.reverse)
        .toList
    }
  }
}
