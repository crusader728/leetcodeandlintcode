package scala.crusader728.leetcode

object CountAllValidPickupDeliveryOptions {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.apply(f => k => m.getOrElseUpdate(k, f(k)))

  }

  val dp: Int => Long = Memo.mutableMapMemo(collection.mutable.Map.empty[Int, Long]).memo {
    case 1 => 1
    case n@_ =>
      val spots = 2 * n - 1
      val x = spots * (spots + 1) / 2
      (dp(n - 1) * x) % 1000000007L
  }

  def countOrders(n: Int): Int = {
    dp(n).toInt
  }
}
