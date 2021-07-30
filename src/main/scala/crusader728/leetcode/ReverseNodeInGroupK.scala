package scala.crusader728.leetcode

object ReverseNodeInGroupK {
  def reverseKGroup(head: ListNode, k: Int): ListNode = {
    @scala.annotation.tailrec
    def forward(n: ListNode, steps: Int): (ListNode, Int) = {
      if (steps == 1) {
        (n, 1)
      } else if (n == null) {
        (n, steps)
      } else {
        forward(n.next, steps - 1)
      }
    }

    @scala.annotation.tailrec
    def reverseFromTo(start: ListNode, end: ListNode, acc: ListNode): (ListNode, ListNode) = {
      if (start == end) {
        start.next = acc
        val tail = forward(start, k)._1
        (start, tail)
      } else {
        val next = start.next
        start.next = acc
        reverseFromTo(next, end, start)
      }
    }

    def helper(prev: ListNode, start: ListNode, end: ListNode, acc: ListNode): ListNode = {
      val (rs, rt) = reverseFromTo(start, end, acc)
      prev.next = rs
      rt
    }

    @scala.annotation.tailrec
    def go(n: ListNode): Unit = {
      if (n != null) {
        val (start, _) = forward(n.next, 1)
        if (start != null) {
          val (end, remaining) = forward(start, k)
          if (remaining == 1 && end != null) {
            val next = helper(n, start, end, end.next)
            go(next)
          }
        }
      }
    }

    if (k == 1) {
      head
    } else {
      val dummy = new ListNode(0, head)
      go(dummy)
      dummy.next
    }
  }
}
