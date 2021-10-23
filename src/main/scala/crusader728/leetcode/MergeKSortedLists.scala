package scala.crusader728.leetcode

object MergeKSortedLists {
  import scala.util.control.TailCalls._



  def mergeKLists(lists: Array[ListNode]): ListNode = {
    if(lists == null || lists.size == 0) {
      null
    } else {
      @scala.annotation.tailrec
      def helper(lists: Array[ListNode], l: Int, r: Int, cont: ListNode => ListNode): ListNode = {
        if(l >= r) {
          cont(null)
        } else if(l == r - 1) {
          cont(lists(l))
        } else {
          val m = l + (r - l) / 2
          helper(lists, l, m, left => lcont(lists, m, r, left, cont))
        }
      }

      def lcont(lists: Array[ListNode], m: Int, r: Int, left: ListNode, cont: ListNode => ListNode): ListNode = {
        helper(lists, m, r, right => rcont(left, right, cont))
      }

      def rcont(left: ListNode, right: ListNode, cont: ListNode => ListNode): ListNode = {
        cont(mergeTwo(left, right))
      }


      helper(lists, 0, lists.length, identity)
    }
  }

  private def mergeTwo(listA: ListNode, listB: ListNode): ListNode = {

    @scala.annotation.tailrec
    def helper(x: ListNode, y: ListNode, cont: ListNode => ListNode): ListNode = (x, y) match {
      case (null, null) => cont(null)
      case (_, null) => cont(x)
      case (null, _) => cont(y)
      case (_, _) => if(x.x < y.x) {
        helper(x.next, y, r => {
          x.next = r
          cont(x)
        })
      } else {
        helper(x, y.next, r => {
          y.next = r
          cont(y)
        })
      }
    }

    helper(listA, listB, identity)
  }

  def main(args: Array[String]): Unit = {
    val input = Array(
      new ListNode(_x = 1, _next = new ListNode(_x = 4, _next = new ListNode(5))),
      new ListNode(_x = 1, _next = new ListNode(_x = 3, _next = new ListNode(4))),
      new ListNode(_x = 2, _next = new ListNode(6))
    )

    println(implicitly[Show[ListNode]].show(mergeKLists(input)))

  }
}
