package scala.crusader728.leetcode



object InsertIntoSortedCircularLinkedList {
  class Node(var _value: Int) {
    var value: Int = _value
    var next: Node = null
  }


  def loop(head: Node): (Node, Node) = {
    @scala.annotation.tailrec
    def go(current: Node, max: Node, min: Node): (Node, Node) = {
      val newMax = if(current.value > max.value) current else max
      val newMin = if(current.value < min.value) current else min
      if(current.next == head) {
        (newMin, newMax)
      } else {
        go(current.next, newMax, newMin)
      }
    }

    go(head, head, head)
  }


  def find(min: Node, max: Node, insertVal: Int): (Node, Node) = {
    @scala.annotation.tailrec
    def go(current: Node): (Node, Node) = {
      if (current.value <= insertVal && insertVal <= current.next.value) {
        (current, current.next)
      } else {
        go(current.next)
      }
    }

      go(min)
    }

  def insert(head: Node, insertVal: Int): Node = head match {
    case null =>
      val newNode = new Node(insertVal)
      newNode.next = newNode
      newNode
    case n if n.next == n =>
      val newNode = new Node(insertVal)
      n.next = newNode
      newNode.next = n
      head
    case _ =>
      val (min, max) = loop(head)
      if(min.value == max.value) {
        val newNode = new Node(insertVal)
        newNode.next = head.next
        head.next = newNode
        head
      } else {
        if(insertVal <= min.value || max.value <= insertVal) {
          val newNode = new Node(insertVal)
          max.next = newNode
          newNode.next = min
          head
        } else {
          val (n, nxt) = find(min, max, insertVal)
          val newNode = new Node(insertVal)
          n.next = newNode
          newNode.next = nxt
          head
        }
      }
  }
}
