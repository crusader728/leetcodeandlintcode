package scala.crusader728.leetcode

object ConvertBinaryNumberInLinkedListToInteger {
  def getDecimalValue(head: ListNode): Int = {
    @scala.annotation.tailrec
    def go(n: ListNode, acc: Int): Int = n match {
      case null => acc
      case _ => go(n.next, acc * 2 + n.x)
    }

    go(head, 0)
  }
}
