package scala.crusader728.leetcode


object DecodeWays {
  import scala.collection.mutable
  abstract class Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    private def memo[K, V](z: (K => V) => K => V): Memo[K, V] = (f: K => V) => z(f)

    def mutableMapMemo[K, V](m: mutable.Map[K, V]): Memo[K, V] = memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  private case class Solver(s: String) {
    private val m = mutable.HashMap.empty[Int, Int]
    val dp: Int => Int = Memo.mutableMapMemo(m) {
      case n if n == s.length => 1
      case n if n == s.length - 1 && s(n) == '0' => 0
      case n if n == s.length - 1 => 1
      case n if s(n) == '0' => 0
      case n if s(n) == '1' && s(n + 1) == '0' => dp(n + 2)
      case n if s(n) == '1' => dp(n + 1) + dp(n + 2)
      case n if s(n) == '2' && s(n + 1) == '0' => dp(n + 2)
      case n if s(n) == '2' && s(n + 1) >= '1' && s(n + 1) <= '6' => dp(n + 1) + dp(n + 2)
      case n@_ => dp(n + 1)
    }
  }

  def numDecodings(s: String): Int = {
    Solver(s).dp(0)
  }
}
