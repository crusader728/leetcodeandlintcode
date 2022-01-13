package scala.crusader728.leetcode.pq

object MeetingRoomII253 {
  def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
    val sorted = intervals.sortBy(arr => arr(0))
    val pq = collection.mutable.PriorityQueue.empty[Array[Int]](Ordering.by[Array[Int], Int](arr => arr(1)).reverse)
    var room = 0
    sorted.foreach { interval =>
      while(pq.nonEmpty && pq.head(1) <= interval(0)) {
        pq.dequeue()
      }
      pq.enqueue(interval)
      room = Math.max(pq.size, room)
    }
    room
  }
}
