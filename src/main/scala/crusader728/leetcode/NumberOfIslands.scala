package scala.crusader728.leetcode

object NumberOfIslands {
  type R = Int
  type C = Int
  type Cord = (R, C)
  type Grid = Array[Array[Char]]

  val getR: Cord => R = _._1
  val getC: Cord => C = _._2

  val deltas: LazyList[Cord] = LazyList((-1, 0), (1, 0), (0, -1), (0, 1))

  val cords: Grid => LazyList[Cord] = g =>
    for {
      i <- LazyList.from(g.indices)
      j <- LazyList.from(g(i).indices)
    } yield (i, j)

  val bfs: Grid => Cord => Set[Cord] = g => init => {
    @scala.annotation.tailrec
    def go(current: Set[Cord], visited: Set[Cord]): Set[Cord] = {
      if(current.isEmpty) {
        visited
      } else {
        val next = for {
          cord <- current
          delta <- deltas
          newR = getR(cord) + getR(delta)
          newC = getC(cord) + getC(delta)
          if newR >= 0 && newR < g.length && newC >= 0 && newC < g(newR).length && !visited.contains((newR, newC)) && g(newR)(newC) != '0'
        } yield (newR, newC)

        val newVisited = visited ++ next
        go(next, newVisited)
      }
    }

    go(Set(init), Set(init))
  }

  def numIslands(grid: Array[Array[Char]]): Int = {

    cords(grid)
      .foldLeft((0, Set.empty[Cord])) {case ((count,visited), cord) => {
        if(visited.contains(cord)) {
          (count, visited)
        } else if(grid(getR(cord))(getC(cord)) == '1'){
          val cells = bfs(grid)(cord)
          (count + 1, visited ++ cells)
        } else {
          (count, visited)
        }
      }}._1
  }
}
