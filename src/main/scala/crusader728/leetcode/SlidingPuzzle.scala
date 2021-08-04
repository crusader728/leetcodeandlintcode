package scala.crusader728.leetcode

object SlidingPuzzle {
  private val finalRepr = "123450"
  private val delta = List((0, 1), (0, -1), (1, 0), (-1, 0))

  case class State(repr: String, emptySpace: (Int, Int))

  private def toRepr(board: Array[Array[Int]]): String = board.map(arr => arr.mkString).mkString

  def slidingPuzzle(board: Array[Array[Int]]): Int = {
    val initRepr = toRepr(board)
    val initPos = for {
      i <- 0 until 2
      j <- 0 until 3
      if(board(i)(j) == 0)
    } yield (i, j)
    val initState = State(initRepr, initPos.head)

    @scala.annotation.tailrec
    def loop(q: List[State], visited: Map[String, Int], step: Int): Int = {
      if(q.isEmpty) {
        -1
      } else if(q.exists(s => s.repr == finalRepr)) {
        step
      } else {
        val newVisited = q.foldRight(visited) { case (s, acc) => acc + (s.repr -> step) }
        val newQ = for {
          s <- q
          (x, y) = s.emptySpace
          (dx, dy) <- delta
          (newX, newY) = (x + dx, y + dy) if 0 <= newX && newX < 2 && 0 <= newY && newY < 3
          repr1 = s.repr.updated(x * 3 + y, s.repr(newX * 3 + newY))
          repr2 = repr1.updated(newX * 3 + newY, '0')
          if newVisited.getOrElse(repr2, Int.MaxValue) > step + 1
        } yield State(repr2, (newX, newY))
        loop(newQ, newVisited, step + 1)
      }
    }

    loop(List(initState), Map(initRepr -> 0), 0)
  }
}
