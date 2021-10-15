package scala.crusader728.leetcode



object BestTimeStockWithCooldown {

  def maxProfit(prices: Array[Int]): Int = {
    val (e, h, c) = prices.foldLeft((0, Int.MinValue, Int.MinValue)) {case ((empty, hold, cooldown), p) => {
      val newEmpty = Math.max(empty, cooldown)
      val newHold = Math.max(empty - p, hold)
      val newCooldown = hold + p
      (newEmpty, newHold, newCooldown)
    }}
    Math.max(e, c)
  }
}
