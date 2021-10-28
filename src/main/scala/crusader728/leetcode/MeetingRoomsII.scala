package scala.crusader728.leetcode

object MeetingRoomsII {
  def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
    val sorted = intervals.sortBy(arr => arr(0))
    val pq = collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)
    sorted.foreach(arr => {
      if(pq.isEmpty) {
        pq.enqueue(arr(1))
      } else if(pq.head > arr(0)) {
        pq.enqueue(arr(1))
      } else {
        pq.dequeue()
        pq.enqueue(arr(1))
      }
    })
    pq.size
  }
}
