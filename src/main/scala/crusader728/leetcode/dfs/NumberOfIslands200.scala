package scala.crusader728.leetcode.dfs

object NumberOfIslands200 {

  val deltas = List((0, 1), (0, -1), (1, 0), (-1, 0))

  def dfs(grid: Array[Array[Char]], i: Int, j: Int, visited: Array[Array[Boolean]]): Unit = {
    visited(i)(j) = true
    for(delta <- deltas) {
      val r = i + delta._1
      val c = j + delta._2
      if(0 <= r && r < grid.length && 0 <= c && c < grid(r).length && grid(r)(c) == '1' && !visited(r)(c)) {
        dfs(grid, r, c, visited)
      }
    }
  }

  def numIslands(grid: Array[Array[Char]]): Int = {
    var count = 0
    val visited = Array.fill(grid.length)(Array.fill[Boolean](grid(0).length)(false))
    grid.indices.foreach(i => {
      grid(i).indices.foreach(j => {
        if(grid(i)(j) == '1' && !visited(i)(j)) {
          dfs(grid, i, j, visited)
          count += 1
        }
      })
    })
    count
  }
}
