package scala.crusader728.leetcode

object MinStack {
  class MinStack() {

    /** initialize your data structure here. */
    type Value = Int
    type Min = Int
    var stack: List[(Value, Min)] = List[(Value, Min)]()

    def push(`val`: Int) {
      stack match {
        case Nil =>
          stack = (`val`, `val`) :: stack
        case (v, m) :: _ => if(`val` < m) {
          stack = (`val`, `val`) :: stack
        } else {
          stack = (`val`, m) :: stack
        }
      }
    }

    def pop() {
      stack = stack.tail
    }

    def top(): Int = {
      stack.head._1
    }

    def getMin(): Int = {
      stack.head._2
    }

  }

}
