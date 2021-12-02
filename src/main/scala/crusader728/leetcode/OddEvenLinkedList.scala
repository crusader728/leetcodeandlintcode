package scala.crusader728.leetcode

object OddEvenLinkedList {
  type Head = ListNode
  type Tail = ListNode
  type Info = (Head, Tail)

  val getHead: Info => Head = _._1
  val getTail: Info => Tail = _._2

  def oddEvenList(head: ListNode): ListNode = {
    def go(idx: Int, n: ListNode): (Info, Info) = n match {
      case null => ((null, null), (null, null))
      case _ => if(idx % 2 == 0) {
        val (odd, even) = go(idx + 1, n.next)
        n.next = getHead(even)
        (odd, (n, if(getTail(even) == null) n else getTail(even)))
      } else {
        val (odd, even) = go(idx + 1, n.next)
        n.next = getHead(odd)
        ((n, if(getTail(odd) == null) n else getTail(odd)), even)
      }
    }

    val (odd, even) = go(1, head)
    if(getTail(odd) == null) {
      null
    } else {
      getTail(odd).next = getHead(even)
      getHead(odd)
    }
  }
}
