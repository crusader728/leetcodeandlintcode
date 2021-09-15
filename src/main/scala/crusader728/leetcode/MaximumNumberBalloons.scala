package scala.crusader728.leetcode

object MaximumNumberBalloons {
  def maxNumberOfBalloons(text: String): Int = {
    "balon".zip(List(1,1,2,2,1)).map{case (k, v) => text.count(_ == k) / v}.min
  }
}
