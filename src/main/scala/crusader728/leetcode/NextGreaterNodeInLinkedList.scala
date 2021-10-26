package scala.crusader728.leetcode

object NextGreaterNodeInLinkedList {
  type Index = Int
  type Value = Int
  type Info = (Value, Index)
  type State = List[Info]
  type Output = List[Info]

  def nextLargerNodes(head: ListNode): Array[Int] = {
    @scala.annotation.tailrec
    def helper(node: ListNode, i: Index, state: State, acc: Output): Output = {
      if(node == null) {
        if(state.isEmpty) {
          acc
        } else {
          state.map(info => (0, info._2)) ++ acc
        }
      } else {
        state match {
          case Nil => helper(node.next, i + 1, (node.x, i) :: state, acc)
          case (v, j) :: _ => if(v >= node.x) {
            helper(node.next, i + 1, (node.x, i) :: state, acc)
          } else {
            helper(node, i, state.tail, (node.x, j) :: acc)
          }
        }
      }
    }

    val output = helper(head, 0, List.empty, List.empty).toArray
    output.sortBy(info => info._2)
      .map(info => info._1)
  }


}
