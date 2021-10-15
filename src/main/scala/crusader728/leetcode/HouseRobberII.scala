package scala.crusader728.leetcode

import scala.collection.mutable

object HouseRobberII {
  trait Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    def memo[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(nums: Array[Int], r: Int) {
    val dp: Int => Int = Memo.mutableMapMemo(new mutable.HashMap[Int, Int]()) {
      case n if n >= r => 0
      case n@_ => Math.max(dp(n + 1), dp(n + 2) + nums(n))
    }
  }

  def rob(nums: Array[Int]): Int = {
    if(nums.length == 1) {
      nums(0)
    } else {
      Math.max(Solver(nums, nums.length).dp(1), Solver(nums, nums.length - 1).dp(0))
    }
  }
}
