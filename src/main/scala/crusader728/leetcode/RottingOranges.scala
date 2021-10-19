package scala.crusader728.leetcode

object RottingOranges {
  type Cord = (Int, Int)
  type Visited = Set[Cord]

  val deltas: List[Cord] = List((0, 1), (0, -1), (1, 0), (-1, 0))

  def orangesRotting(grid: Array[Array[Int]]): Int = {
    val coordinates = cords(grid)
      val seeds = coordinates
      .filter(cord => grid(cord._1)(cord._2) == 2)
    @scala.annotation.tailrec
    def bfs(q: List[Cord], visited: Visited, x: Int): (Visited, Int) = {
      if(q.isEmpty) {
        (visited, x)
      } else {
        val newLevel = for {
          cord <- q
          delta <- deltas
          newX = cord._1 + delta._1
          newY = cord._2 + delta._2
          if grid.indices.contains(newX) && grid(newX).indices.contains(newY) && !visited.contains((newX, newY)) && grid(newX)(newY) == 1
        } yield (newX, newY)
        bfs(newLevel, newLevel.foldLeft(visited)(_ + _), x + 1)
      }
    }

    val (visited, depth) = bfs(seeds, Set.from(seeds), 0)
    if(visited.isEmpty) {
      if(coordinates.forall(c => grid(c._1)(c._2) != 1)) {
        0
      } else {
        -1
      }
    } else {
      if(coordinates.exists(c => grid(c._1)(c._2) == 1)) {
        -1
      } else {
        depth -1
      }
    }
  }

  private def cords(grid: Array[Array[Int]]): List[Cord] = {
    for {
      i <- List.from(grid.indices)
      j <- List.from(grid(i).indices)
    } yield (i, j)
  }
}
