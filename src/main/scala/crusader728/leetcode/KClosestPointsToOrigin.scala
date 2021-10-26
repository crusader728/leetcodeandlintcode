package scala.crusader728.leetcode

object KClosestPointsToOrigin {
  type Cord = (Int, Int)

  val dist: Cord => Int = { case (x, y) => x * x + y * y }

  type State = collection.mutable.PriorityQueue[Cord]

  implicit val cordOrdering: Ordering[Cord] = Ordering.by(dist)

  def step(k: Int)(p: Array[Int], state: State): State = {
    state.enqueue((p(0), p(1)))
    if(state.size > k) {
      state.dequeue()
    }
    state
  }


  def kClosest(points: Array[Array[Int]], k: Int): Array[Array[Int]] = {
    val result = points.foldRight(collection.mutable.PriorityQueue.empty[Cord])(step(k))
    result.map {case (x, y) => Array(x, y) }
      .toArray
  }

}
