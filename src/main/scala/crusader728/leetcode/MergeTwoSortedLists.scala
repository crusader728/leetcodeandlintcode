package scala.crusader728.leetcode

object MergeTwoSortedLists {
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {

    @scala.annotation.tailrec
    def helper(h1: ListNode, h2: ListNode, cont: ListNode => ListNode): ListNode = {
      (h1, h2) match {
        case (null, null) => cont(null)
        case (_, null) => cont(h1)
        case (null, _) => cont(h2)
        case (_, _) => if(h1.x < h2.x) {
          helper(h1.next, h2, sub => {
            h1.next = sub
            cont(h1)
          })
        } else {
          helper(h1, h2.next, sub => {
            h2.next = sub
            cont(h2)
          })
        }
      }
    }

    helper(l1, l2, identity)
  }
}
