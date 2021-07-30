package scala.crusader728.leetcode

object MakeALargeIsland {
  private val delta = Array((0, 1), (0, -1), (1, 0), (-1, 0))
  def largestIsland(grid: Array[Array[Int]]): Int = {
    val n = grid.size
    val memo = Array.fill[Int](n, n)(0)
    val sizeMap = scala.collection.mutable.HashMap.empty[Int, Int]

    def dfs(x: Int, y: Int, dfsId: Int, sum: Int): Int = {
      memo(x)(y) = dfsId
      delta.foldLeft(sum + 1) { case (acc, (dx, dy)) => {
        val newX = x + dx
        val newY = y + dy
        if(newX >= 0 && newX < n &&
          newY >= 0 && newY < n &&
          grid(newX)(newY) == 1 &&
          memo(newX)(newY) == 0) {
          dfs(newX, newY, dfsId, acc)
        } else {
          acc
        }
      }}
    }

    (0 until n).foldLeft(1) {(id, i) => {
      (0 until n).foldLeft(id) {(dfsId, j) => {
        if(grid(i)(j) == 0 || memo(i)(j) != 0) {
          dfsId
        } else {
          val size = dfs(i, j, dfsId, 0)
          sizeMap += (dfsId -> size)
          dfsId + 1
        }
      }}
    }}

    (0 until n).foldLeft(Int.MinValue) {case (max, i) => {
      (0 until n).foldLeft(max) {case (curMax, j) => {
        if(grid(i)(j) == 1) {
          val dfsId = memo(i)(j)
          Math.max(curMax, sizeMap(dfsId))
        } else {
          val sets = delta
            .map {case (dx, dy) => (i + dx, j + dy) }
            .filter {case (x, y) => 0 <= x && x < n && 0 <= y && y < n}
            .map {case (x, y) => memo(x)(y)}
            .filter(id => id != 0)
            .toSet

          Math.max(curMax, sets.foldLeft(1) {case (acc, id) => acc + sizeMap(id)})
        }
      }}
    }}
  }
}
