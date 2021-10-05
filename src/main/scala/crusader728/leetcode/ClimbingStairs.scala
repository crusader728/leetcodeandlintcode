package scala.crusader728.leetcode

object ClimbingStairs {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](g: (K => V) => K => V): Memo[K, V] = (f: K => V) => g(f)
    def memoWithHashMap[K, V]: Memo[K, V] = {
      val m = scala.collection.mutable.HashMap.empty[K, V]
      Memo[K, V](f => k => m.getOrElseUpdate(k, f(k)))
    }
  }

  val memoized: Int => Int = Memo.memoWithHashMap[Int, Int].memo {
    case 0 => 1
    case 1 => 1
    case n@_ => memoized(n - 2) + memoized(n - 1)
  }

  def climbStairs(n: Int): Int = {
    memoized(n)
  }

}
