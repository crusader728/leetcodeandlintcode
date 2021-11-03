package scala.crusader728.leetcode

object MaximumSubArray {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.apply(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(nums: Array[Int], m: collection.mutable.Map[Int, Int]) {
    val dp: Int => Int = Memo.mutableMapMemo(m).memo {
      case 0 => nums(0)
      case i@_ => Math.max(nums(i), dp(i - 1) + nums(i))
    }
  }
  def maxSubArray(nums: Array[Int]): Int = {
    val solver = Solver(nums, collection.mutable.Map.empty[Int, Int])
    nums.indices.map(i => solver.dp(i)).max
  }
}
