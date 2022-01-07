package scala.crusader728.leetcode.dp

object BurstBallons312 {
  type I = Int
  type V = Int

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = (f: K => V) => k => m.getOrElseUpdate(k, f(k))
  }

  case class Solver(nums: Array[Int], m: collection.mutable.Map[(Int, Int), V]) {
    val dp: ((I, I)) => V = Memo.mutableMapMemo(m).memo {
      case (l, r) if l == r && l == 0 && r == nums.length - 1 => nums(l)
      case (l, r) if l == r && l == 0 => nums(l) * nums(l + 1)
      case (l, r) if l == r && r == nums.length - 1 => nums(l - 1) * nums(l)
      case (l, r) if l == r => nums(l - 1) * nums(l) * nums(l + 1)
      case (l, r) => {
        val left = if(l - 1 < 0) 1 else nums(l - 1)
        val right = if(r + 1 > nums.length - 1) 1 else nums(r + 1)
        var max = Int.MinValue
        (l to r).foreach(i => {
          if(i == l) {
            max = Math.max(max, dp(i + 1, r) + nums(i) * left * right)
          } else if(i == r) {
            max = Math.max(max, dp(l, i - 1) + nums(i) * left * right)
          } else {
            max = Math.max(max, dp(i + 1, r) + dp(l, i - 1) + nums(i) * left * right)
          }
        })
        max
      }
    }
  }

  def maxCoins(nums: Array[Int]): Int = {
    val m = collection.mutable.HashMap.empty[(I, I), V]
    val solver = Solver(nums, m)
    val result = solver.dp((0, nums.length - 1))
    println(m)
    result
  }

  def main(args: Array[String]): Unit = {
    println(maxCoins(Array(1,5)))
  }
}
