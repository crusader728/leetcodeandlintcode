package scala.crusader728.leetcode

object FindMedianFromDataStream {
  val bottom = scala.collection.mutable.PriorityQueue.empty[Int]
  val top = scala.collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)

  /** initialize your data structure here. */
  def addNum(num: Int) {
    bottom.enqueue(num)
    top.enqueue(bottom.dequeue())
    if(top.size > bottom.size) {
      bottom.enqueue(top.dequeue())
    }
  }

  def findMedian(): Double = {
    if(top.size == bottom.size) {
      (top.head + bottom.head) / 2.toDouble
    } else {
      bottom.head.toDouble
    }
  }

}
