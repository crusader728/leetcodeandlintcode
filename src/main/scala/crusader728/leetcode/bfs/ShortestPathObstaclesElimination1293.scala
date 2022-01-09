package scala.crusader728.leetcode.bfs

object ShortestPathObstaclesElimination1293 {
  type R = Int
  type C = Int
  type Cord = (R, C)

  val mkCord: R => C => Cord = r => c => (r, c)
  val getR: Cord => R = _._1
  val getC: Cord => C = _._2
  val addCord: Cord => Cord => Cord = c1 => c2 => mkCord(getR(c1) + getR(c2))(getC(c1) + getC(c2))

  type Grid = Array[Array[Int]]
  val value: Grid => Cord => Int = g => c => g(getR(c))(getC(c))
  val inbound: Grid => Cord => Boolean = g => cord => {
    val r = getR(cord)
    val c = getC(cord)
    0 <= r && r < g.length && 0 <= c && c < g(r).length
  }

  type Charge = Int
  type Step = Int
  type State = (Cord, Charge)
  type Info = (Cord, Step, Charge)

  val getCord: State => Cord = _._1
  val getCharge: State => Charge = _._2
  val mkState: Cord => Charge => State = c => charge => (c, charge)
  val mkInfo: Cord => Step => Charge => Info = c => s => charge => (c, s, charge)

  val deltas: List[Cord] = List((0, 1), (0, -1), (1, 0), (-1, 0))

  def shortestPath(grid: Array[Array[Int]], k: Int): Int = {
    val visitedState = collection.mutable.HashSet.empty[State]
    val queue = collection.mutable.Queue.empty[Info]
    queue.enqueue(mkInfo((0, 0))(0)(k))
    @scala.annotation.tailrec
    def bfs(): Int = {
      if(queue.isEmpty) {
        -1
      } else if(queue.head._1 == (grid.length - 1, grid(0).length - 1)) {
        queue.head._2
      } else {
        val info = queue.dequeue()
        val state = mkState(info._1)(info._3)
        visitedState += state
        for (delta <- deltas) {
          val newCord = addCord(delta)(getCord(state))
          if(inbound(grid)(newCord) && (value(grid)(newCord) == 0 || (value(grid)(newCord) == 1 && (getCharge(state) != 0)))) {
            val newCharge = if(value(grid)(newCord) == 0 || (value(grid)(newCord) == 1 && getCharge(state) == 0)) getCharge(state) else getCharge(state) - 1
            val newStep = info._2 + 1
            val newState = mkState(newCord)(newCharge)
            if(!visitedState.contains(newState)) {
              queue.enqueue(mkInfo(newCord)(newStep)(newCharge))
            }
          }
        }
        bfs()
      }
    }

    bfs()
  }

  def main(args: Array[String]): Unit = {
    println(shortestPath(
      Array(
        Array(0, 0, 0),
        Array(1, 1, 0),
        Array(0, 0, 0),
        Array(0, 1, 1),
        Array(0, 0, 0)
      ), 1
    ))
  }
}
