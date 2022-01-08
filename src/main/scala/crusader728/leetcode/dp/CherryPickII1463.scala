package scala.crusader728.leetcode.dp

object CherryPickII1463 {
  def cherryPickup(grid: Array[Array[Int]]): Int = {
    val solver = Solver(grid)
    solver.dp((0, 0), (0, grid(0).length - 1))
  }

  type R = Int
  type C = Int
  type Cord = (R, C)

  val makeCord: R => C => Cord = r => c => (r, c)
  val getR: Cord => R = _._1
  val getC: Cord => C = _._2
  val add: Cord => Cord => Cord = cord1 => cord2 => makeCord(getR(cord1) + getR(cord2))(getC(cord1) + getC(cord2))

  type Grid = Array[Array[Int]]
  val value: Grid => Cord => Int = g => cord => g(getR(cord))(getC(cord))
  val row: Grid => R => Array[Int] = g => r => g(r)
  val inbound: Grid => Cord => Boolean = g => cord => 0 <= getR(cord) && getR(cord) < g.length && 0 <= getC(cord) && getC(cord) < g(getR(cord)).length

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = new Memo[K, V] {
      override def memo(f: K => V): K => V = k => m.getOrElseUpdate(k, f(k))
    }
  }

  case class Solver(grid: Grid) {
    private val deltas = List((1, -1), (1, 0), (1, 1))
    type K = (Cord, Cord)
    type V = Int
    val dp: K => V = Memo.mutableMapMemo[K, V](collection.mutable.Map.empty[K, V]).memo {
      case (cord1, cord2) if getR(cord1) != getR(cord2) => throw new RuntimeException
      case (cord1, cord2) if getR(cord1) == grid.length => 0
      case (cord1, cord2) =>
        val next = for {
          delta1 <- deltas
          delta2 <- deltas
          newCord1 = add(cord1)(delta1)
          newCord2 = add(cord2)(delta2)
          if inbound(grid)(newCord1) && inbound(grid)(newCord2)
        } yield ((newCord1, newCord2))
        val current = if(getC(cord1) == getC(cord2)) value(grid)(cord1) else value(grid)(cord1) + value(grid)(cord2)
        next.map(dp).maxOption.getOrElse(0) + current
    }
  }



  def main(args: Array[String]): Unit = {
    println(cherryPickup(
      Array(
        Array(1,0,0,0,0,0,1),
        Array(2,0,0,0,0,3,0),
        Array(2,0,9,0,0,0,0),
        Array(0,3,0,5,4,0,0),
        Array(1,0,2,3,0,0,6)
      )
    ))
  }
}
