package scala.crusader728.leetcode.stack

object MaxStack716 {
  class MaxStack() {
    type M = Int
    type Info = (Int, M)
    /** initialize your data structure here. */
    var stack = List.empty[Info]

    def push(x: Int) {
      if(stack.isEmpty) {
        stack = (x, x) :: Nil
      } else {
        val max = stack.head._2
        stack = if(max > x) {
          (x, max) :: stack
        } else {
          (x, x) :: stack
        }
      }
    }

    def pop(): Int = {
      val value = stack.head._1
      stack = stack.tail
      value
    }

    def top(): Int = {
      stack.head._1
    }

    def peekMax(): Int = {
      stack.head._2
    }

    def popMax(): Int = {
      val currentMax = stack.head._2
      val values = stack.takeWhile(info => info._1 != currentMax).map(_._1).reverse
      stack = stack.dropWhile(info => info._1 != currentMax).tail
      values.foreach(i => this.push(i))
      currentMax
    }

  }
}
