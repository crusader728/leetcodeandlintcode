package scala.crusader728.leetcode

import scala.collection.View

object MeetingRoomsII {
  abstract class Point extends Product with Serializable {
    def id: Int
    def ts: Int
  }
  case class Start(id: Int, ts: Int) extends Point
  case class End(id: Int, ts: Int) extends Point

  implicit val pointOrdering: Ordering[Point] = new Ordering[Point] {
    override def compare(x: Point, y: Point): Int = {
      val tsOrdering = implicitly[Ordering[Int]]
      val tsCompare = tsOrdering.compare(x.ts, y.ts)
      if(tsCompare != 0) {
        tsCompare
      } else {
        (x, y) match {
          case (Start(_, _), Start(_, _)) => -1
          case (Start(_, _), End(_, _)) => 1
          case (End(_, _), Start(_, _)) => -1
          case _ => 1
        }
      }
    }
  }

  val points: Array[Array[Int]] => Array[Point] = arr => {
    for {
      (interval, idx) <- arr.zipWithIndex
      start = Start(idx, interval(0))
      end = End(idx, interval(1))
      p <- Array(start, end)
    } yield p
  }

  def sweepingLine(intervals: Array[Array[Int]]): Int =  points(intervals)
    .sorted
    .foldLeft((0, Set.empty[Int])) { case ((max, set), point) => {
      point match {
        case Start(id, _) => {
          val newSet = set + id
          (Math.max(max, newSet.size), newSet)
        }
        case End(id, _) => {
          val newSet = set - id
          (max, newSet)
        }
      }
    }}._1


  def byPriorityQueue(interval: Array[Array[Int]]): Int = {
    val pq = collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)
    interval.sortBy(arr => arr(0))
      .foldLeft(pq) {case (acc, arr) => {
        if(acc.isEmpty) {
          acc.enqueue(arr(1))
          acc
        } else {
          val top = acc.head
          if(top > arr(0)) {
            acc.enqueue(arr(1))
            acc
          } else {
            acc.dequeue()
            acc.enqueue(arr(1))
            acc
          }
        }
      }}.size
  }

  def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
    val pq = collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)
    intervals.sortBy(arr => arr(0))
      .foldLeft(pq) {case (acc, arr) => {
        if(acc.isEmpty) {
          acc.enqueue(arr(1))
          acc
        } else {
          val top = acc.head
          if(top > arr(0)) {
            acc.enqueue(arr(1))
            acc
          } else {
            acc.dequeue()
            acc.enqueue(arr(1))
            acc
          }
        }
      }}.size
  }
}
