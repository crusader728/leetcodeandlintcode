package scala.crusader728.leetcode

object NumberOfIslands {
  type R = Int
  type C = Int
  type Cord = (Int, Int)
  type Grid = Array[Array[Char]]
  val deltas = List((1, 0), (-1, 0), (0, 1), (0, -1))
    def numIslands(grid: Array[Array[Char]]): Int = {
      val visited = collection.mutable.HashSet.empty[Cord]
      val cords = for {
        i <- grid.indices
        j <- grid(i).indices
      } yield (i, j)
      cords.foldLeft(0) {case (acc, cord) => {
        if(grid(cord._1)(cord._2) != '1') {
          acc
        } else if(visited.contains(cord)) {
          acc
        } else {
          def dfs(v: Cord): Unit = {
            visited += v
            deltas.foreach { case (dx, dy) => {
              val newR = dx + v._1
              val newC = dy + v._2
              if(newR >= 0 && newR < grid.length && newC >= 0 && newC < grid(newR).length && grid(newR)(newC) == '1') {
                dfs((newR, newC))
              }
            }}
          }
          dfs(cord)
          acc + 1
        }
      }}
    }
  }
