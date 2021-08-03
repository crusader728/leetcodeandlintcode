package scala.crusader728.leetcode

object BrickWall {
  def leastBricks(wall: List[List[Int]]): Int = {
    val edges = wall.foldRight(Map[Int, Int]()) {case (l, acc) => {
      val scan = l.scanLeft(0)(_ + _).tail.init
      scan.foldRight(acc) {case (n, m) => {
        if(m.contains(n)) {
          m + (n -> (m(n) + 1))
        } else {
          m + (n -> 1)
        }
      }}
    }}
    if(edges.values.isEmpty) {
      wall.length
    } else {
      wall.length - edges.values.max
    }

  }
}
