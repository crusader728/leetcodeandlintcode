package scala.crusader728.leetcode

object FlattenMultilevelList {
  def flatten(head: Node): Node = {
    def dfs(head: Node): (Node, Node) = {
      if(head.next == null && head.child == null) {
        (head, head)
      } else if(head.child == null && head.next != null) {
        val (h1, t1) = dfs(head.next)
        (head, t1)
      } else if(head.child != null && head.next == null) {
        val (h1, t1) = dfs(head.child)
        head.child = null
        head.next = h1
        h1.prev = head
        (head, t1)
      } else {
        val (h1, t1) = dfs(head.child)
        val (h2, t2) = dfs(head.next)
        head.child = null
        head.next = h1
        h1.prev = head
        t1.next = h2
        h2.prev = t1
        (head, t2)
      }
    }
    if(head == null) {
      null
    } else {
      dfs(head)._1
    }
  }
}
