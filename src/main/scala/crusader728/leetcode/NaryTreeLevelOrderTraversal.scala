package scala.crusader728.leetcode

object NaryTreeLevelOrderTraversal {
  def levelOrder(root: NaryTreeNode): List[List[Int]] = {
    @scala.annotation.tailrec
    def loop(q: List[NaryTreeNode], acc: List[List[Int]]): List[List[Int]] = {
      q match {
        case Nil => acc.reverse
        case _ => {
          val newAcc = q.map(n => n.value) :: acc
          loop(q.flatMap(node => node.children), newAcc)
        }
      }
    }

    root match {
      case null => Nil
      case _ => loop(List(root), Nil)
    }
  }
}
