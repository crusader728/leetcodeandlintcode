package scala.crusader728.leetcode

import scala.collection.mutable


object MinCostClimbingStairs {
  trait Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    def memo[K, V](gen: (K => V) => K => V): Memo[K, V] = (f: K => V) => gen(f)

    def mutableMapMemo[K, V](a: collection.mutable.Map[K, V]): Memo[K, V] = Memo.memo[K, V](f => k => a.getOrElseUpdate(k, f(k)))
  }

  case class Solver(cost: Array[Int]) {
    val f: Int => Int = Memo.mutableMapMemo(new mutable.WeakHashMap[Int, Int]) {
      case n if n >= cost.length => 0
      case i@_ => cost(i) + Math.min(f(i + 1), f(i + 2))
    }
  }

  def minCostClimbingStairs(cost: Array[Int]): Int = {
    val solver = Solver(cost)
    Math.min(solver.f(0), solver.f(1))
  }
}
