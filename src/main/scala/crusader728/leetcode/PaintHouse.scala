package scala.crusader728.leetcode

object PaintHouse {
  type Color = Int

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(costs: Array[Array[Int]]) {
    type KEY = (Int, Color)

    val dp: KEY => Int = Memo.mutableMapMemo[KEY, Int](collection.mutable.Map.empty).memo {
      case (n, _) if n >= costs.length => 0
      case (n, 0) => Math.min(dp((n + 1, 1)), dp((n + 1, 2))) + costs(n)(0)
      case (n, 1) => Math.min(dp((n + 1, 0)), dp((n + 1, 2))) + costs(n)(1)
      case (n, 2) => Math.min(dp((n + 1, 0)), dp((n + 1, 1))) + costs(n)(2)
    }
  }

  def minCost(costs: Array[Array[Int]]): Int = {
    val solver = Solver(costs)
    Math.min(Math.min(solver.dp(0, 0), solver.dp(0, 1)), solver.dp(0, 2))
  }
}
