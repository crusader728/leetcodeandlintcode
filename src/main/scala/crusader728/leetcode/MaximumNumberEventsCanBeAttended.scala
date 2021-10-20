package scala.crusader728.leetcode


object MaximumNumberEventsCanBeAttended {


  def maxEvents(events: Array[Array[Int]]): Int = {

    val sortedByStartedDate = events.sortWith {case (a1, a2) => if(a1(0) == a2(0)) {
      a1(1) < a2(1)
    } else {
      a1(0) < a2(0)
    }}

    val pq = collection.mutable.PriorityQueue.empty[Int](Ordering.Int.reverse)

    @scala.annotation.tailrec
    def addEndTimeToPq(i: Int, d: Int): Int = {
      if(i < sortedByStartedDate.length && sortedByStartedDate(i)(0) <= d) {
        pq.enqueue(sortedByStartedDate(i)(1))
        addEndTimeToPq(i + 1, d)
      } else {
        i
      }
    }

    @scala.annotation.tailrec
    def pop(d: Int): Unit = {
      if(pq.nonEmpty && pq.head < d) {
        pq.dequeue()
        pop(d)
      }
    }

    @scala.annotation.tailrec
    def loop(d: Int, i: Int, acc: Int): Int = {
      if(d > 100000) {
        acc
      } else {
        val j = addEndTimeToPq(i, d)
        pop(d)
        if(pq.nonEmpty) {
          pq.dequeue()
          println(pq)
          loop(d + 1, j, acc + 1)
        } else {
          loop(d + 1, j, acc)
        }
      }
    }

    loop(1, 0, 0)

  }

  def main(args: Array[String]): Unit = {
    println(maxEvents(Array(Array(1,2), Array(2,3), Array(3,4), Array(1,2))))
  }
}
