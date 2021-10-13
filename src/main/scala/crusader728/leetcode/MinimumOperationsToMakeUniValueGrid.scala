package scala.crusader728.leetcode

object MinimumOperationsToMakeUniValueGrid {
  def minOperations(grid: Array[Array[Int]], x: Int): Int = {
    val sorted = (for {
      i <- grid.indices
      j <- grid(i).indices
    } yield grid(i)(j)).toList.sorted

    val median = sorted(sorted.length / 2)
    val steps = sorted.map { i =>
      val dist = Math.abs(median - i)
      if(dist % x == 0) {
        Some(dist / x)
      } else {
        None
      }
    }
    sequence(steps).map(_.sum).getOrElse(-1)
  }

  def sequence(xs: List[Option[Int]]): Option[List[Int]] = {
    xs.foldRight(Option(List.empty[Int])) {case (i, acc) => {
      i match {
        case None => None
        case Some(v) => acc.map(x => x.prepended(v))
      }
    }}
  }
}
