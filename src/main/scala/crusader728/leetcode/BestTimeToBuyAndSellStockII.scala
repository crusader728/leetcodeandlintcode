package scala.crusader728.leetcode

object BestTimeToBuyAndSellStockII {
  def maxProfit(prices: Array[Int]): Int = {
    val (e, h) = prices.tail.foldLeft((0, 0 - prices(0))) {case ((empty, hold), i) => {
      val newEmpty = Math.max(empty, hold + i)
      val newHold = Math.max(hold, empty - i)
      (newEmpty, newHold)
    }}
    Math.max(e, h)
  }
}
