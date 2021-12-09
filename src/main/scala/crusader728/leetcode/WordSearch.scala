package scala.crusader728.leetcode

object WordSearch {
  type R = Int
  type C = Int
  type Cord = (R, C)

  val getR: Cord => R = _._1
  val getC: Cord => C = _._2

  type Board = Array[Array[Char]]
  type Visited = Set[Cord]

  val deltas = List((-1, 0), (1, 0), (0, -1), (0, 1))

  def dfs(board: Board, w: String, current: Cord, i: Int, visited: Visited): Boolean = {
    if(board(getR(current))(getC(current)) != w(i)) {
      false
    } else if(i == w.length - 1) {
      true
    } else {
      val newVisited = visited + current
      (for {
        delta <- deltas
        newR = getR(delta) + getR(current)
        newC = getC(delta) + getC(current)
        if newR >= 0 && newR < board.length && newC >= 0 && newC < board(newR).length && !newVisited.contains((newR, newC))
      } yield (newR, newC))
        .foldLeft(false) {case (acc, next) => {
          acc | dfs(board, w, next, i + 1, newVisited)
        }}
    }
  }

  def exist(board: Array[Array[Char]], word: String): Boolean = {
    (for {
      i <- board.indices
      j <- board(i).indices
    } yield (i, j)).exists(cord => dfs(board, word, cord, 0, Set.empty))
  }

}
