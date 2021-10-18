package scala.crusader728.leetcode

object BestTimeToBuyAndSellStock {
  def maxProfit(prices: Array[Int]): Int = {
    val (_, _, r) = prices.foldRight((List.empty[Int], Int.MinValue, Int.MinValue)) {case (i, (stack, base, max)) => stack match {
      case Nil => (List(i), i, max)
      case x :: xs => if(i < x) {
        (i :: xs, base, Math.max(max, base - i))
      } else if(i < base) {
        (x :: xs, base, max)
      } else {
        (List(i), i, max)
      }
    }}

    Math.max(0, r)
  }
}
