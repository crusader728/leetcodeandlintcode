package scala.crusader728.leetcode

object PerfectSquares {
  def numSquares(n: Int): Int = {
    dp(n)
  }

  trait Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    def memo[K, V](gen: (K => V) => K => V): Memo[K, V] = (f: K => V) => gen(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  val dp: Int => Int = Memo.mutableMapMemo(new collection.mutable.WeakHashMap[Int, Int]) {
    case 0 => 0
    case 1 => 1
    case n@_ => (1 to Math.floor(Math.sqrt(n)).toInt)
      .map(i => (n / (i * i), n % (i * i)))
      .map { case (q, r) => q + dp(r) }
      .min
  }


}
