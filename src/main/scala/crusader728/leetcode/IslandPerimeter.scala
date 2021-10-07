package scala.crusader728.leetcode

object IslandPerimeter {
  import scala.util.control.TailCalls._

  val delta: List[(Int, Int)] = List((0, 1), (0, -1), (1, 0), (-1, 0))
  def islandPerimeter(grid: Array[Array[Int]]): Int = {
    indices(grid).dropWhile { case (i, j) => grid(i)(j) != 1 }
      .headOption
      .map { t =>
        val visited = scala.collection.mutable.HashSet.empty[(Int, Int)]
        bfs(grid, t).foldLeft(0) { case (acc, pos@(x, y)) =>
          val minus = delta.map { case (dx, dy) => (x + dx, y + dy) }.map(visited.contains).count(identity)
          visited += pos
          acc + 4 - 2 * minus
        }
      }
      .getOrElse(0)
  }

  def indices(grid: Array[Array[Int]]): LazyList[(Int, Int)] =
    for {
      i <- LazyList.range(0, grid.length)
      j <- LazyList.range(0, grid(i).length)
    } yield (i, j)

  def bfs(grid: Array[Array[Int]], start: (Int, Int)): LazyList[(Int, Int)] = {
    val visited = scala.collection.mutable.HashSet.empty[(Int, Int)]
    visited += start
    def loop(l: LazyList[(Int, Int)]): TailRec[LazyList[(Int, Int)]] = {
      if(l.isEmpty) {
        done(l)
      } else {
        val (x, y) = l.head
        val children = delta
          .map {case (dx, dy) => (x + dx, y + dy)}
          .filter {case (i, j) => 0 <= i && i < grid.length && 0 <= j && j < grid(i).length && grid(i)(j) == 1 && !visited.contains((i, j))}
        visited.addAll(children)
        tailcall(loop(l.tail.appendedAll(children))).map(r => r.prepended(l.head))
      }
    }

    loop(LazyList(start)).result
  }
}
