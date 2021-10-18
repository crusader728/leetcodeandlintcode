package scala.crusader728.leetcode

object KClosestPointsToOrigin {
  type Cord = (Int, Int)

  case class Info(p: Cord, dist: Int)

  implicit val infoOrdering: Ordering[Info] = Ordering.by(info => info.dist)

  def kClosest(points: Array[Array[Int]], k: Int): Array[Array[Int]] = {
    points
      .foldRight(collection.mutable.PriorityQueue.empty[Info]) { case (arr, pq) =>
        val dist = arr(0) * arr(0) + arr(1) * arr(1)
        pq.enqueue(Info((arr(0), arr(1)), dist))
        if (pq.size > k) {
          pq.dequeue
        }
        pq
      }
      .map(info => Array(info.p._1, info.p._2)).toArray

  }
}
