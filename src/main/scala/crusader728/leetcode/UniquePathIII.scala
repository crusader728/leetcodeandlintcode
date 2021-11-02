package scala.crusader728.leetcode

object UniquePathIII {
  type Row = Int
  type Col = Int
  type Cord = (Int, Int)
  val getRow: Cord => Row = _._1
  val getCol: Cord => Col = _._2

  type Visited = Set[Cord]

  type Board = Array[Array[Int]]

  val blanks: Board => Int = board => board.indices.foldLeft(0) {(acc, i) => {
    board(i).indices.foldLeft(acc) {(a, j) => {
      if(board(i)(j) == 0 || board(i)(j) == 2) {
        a + 1
      } else {
        a
      }
    }}
  }}

  val deltas = List((-1, 0), (1, 0), (0, -1), (0, 1))

  val backtrack: Board => Cord => Visited => Int => Int = b => c => visited => remains => {
    println(c, visited, remains)
    if(b(getRow(c))(getCol(c)) == 2) {
      if(remains == 0) {
        1
      } else {
        0
      }
    } else {
      val newVisited = visited + c
      val next = for {
        delta <- deltas
        newRow = getRow(delta) + getRow(c)
        newCol = getCol(delta) + getCol(c)
        newCord = (newRow, newCol)
        if newRow >= 0 && newRow < b.length && newCol >= 0 && newCol < b(newRow).length && b(newRow)(newCol) != -1 && !newVisited.contains(newCord)
      } yield newCord
      next.map(cord => backtrack(b)(cord)(newVisited)(remains - 1)).sum
    }
  }

  def uniquePathsIII(grid: Array[Array[Int]]): Int = {
    val blankCells = blanks(grid)
    val start = for {
      i <- grid.indices
      j <- grid(i).indices
      if grid(i)(j) == 1
    } yield (i, j)
    start.headOption
      .map(cord => backtrack(grid)(cord)(Set.empty)(blankCells))
      .getOrElse(-1)
  }

  def main(args: Array[String]): Unit = {
    println(uniquePathsIII(Array(Array(1,0,0,0), Array(0,0,0,0), Array(0,0,2,-1))))
  }
}
