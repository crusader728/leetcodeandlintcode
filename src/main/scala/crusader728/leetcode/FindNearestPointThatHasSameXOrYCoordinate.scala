package scala.crusader728.leetcode

object FindNearestPointThatHasSameXOrYCoordinate {
  type Idx = Int
  def nearestValidPoint(x: Int, y: Int, points: Array[Array[Int]]): Int = {
    val infoOrdering: Ordering[Idx] = Ordering.by(i => Math.abs(points(i)(0) - x) + Math.abs(points(i)(1) - y))

    points.indices
      .filter(i => points(i)(0) == x || points(i)(1) == y)
      .minOption(infoOrdering)
      .getOrElse(-1)
  }
}
