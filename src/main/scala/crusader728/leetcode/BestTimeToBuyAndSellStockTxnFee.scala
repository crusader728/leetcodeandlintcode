package scala.crusader728.leetcode

object BestTimeToBuyAndSellStockTxnFee {
  def maxProfit(prices: Array[Int], fee: Int): Int = {
    val (finalSold, finalHold) = prices.view.tail.foldLeft((0, 0 - prices(0))) {case ((sold, hold), p) => {
      val nextSold = Math.max(sold, hold + p - fee)
      val nextHold = Math.max(hold, sold - p)
      (nextSold, nextHold)
    }}

    Math.max(finalSold, finalHold)
  }
}
