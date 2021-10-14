package scala.crusader728.leetcode

object HouseRobber {
  trait Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    def memo[K, V](gen: (K => V) => K => V): Memo[K, V] = (f: K => V) => gen(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(nums: Array[Int]) {
    val dp: Int => Int = Memo.mutableMapMemo(collection.mutable.WeakHashMap.empty[Int, Int]) {
      case n if n >= nums.length => 0
      case i@_ => Math.max(nums(i) + dp(i + 2), dp(i + 1))
    }
  }
  def rob(nums: Array[Int]): Int = {
    Solver(nums).dp(0)
  }

  def main(args: Array[String]): Unit = {
    rob(Array(2,3,2))
  }
}
