package scala.crusader728.leetcode.pq

object MinimumNumberArrowsToBurstBalloons452 {
  def findMinArrowShots(points: Array[Array[Int]]): Int = {
    val sorted = points.sortBy(arr => arr(0))
    var count = 0
    val pq = collection.mutable.PriorityQueue.empty[Array[Int]](Ordering.by[Array[Int], Int](arr => arr(1)).reverse)
    for (interval <- sorted) {
      if(pq.isEmpty) {
        pq.enqueue(interval)
      } else {
        println(s"${interval(0)}, ${interval(1)}, pq = $pq, pq.head = {${pq.head(0)}, ${pq.head(1)}")
        if(pq.head(1) > interval(1)) {
          pq.dequeue()
          pq.enqueue(interval)
        } else if(pq.head(1) < interval(0)) {
          pq.dequeue()
          count += 1
          pq.enqueue(interval)
        }
      }
    }
    if(pq.nonEmpty) {
      count += 1
    }
    count
  }

  def main(args: Array[String]): Unit = {
    println(findMinArrowShots(Array(Array(3,9),Array(7,12),Array(3,8),Array(6,8),Array(9,10),Array(2,9),Array(0,9),Array(3,9),Array(0,6),Array(2,8))))
  }
}
