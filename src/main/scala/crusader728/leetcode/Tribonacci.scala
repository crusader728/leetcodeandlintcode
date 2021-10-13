package scala.crusader728.leetcode

object Tribonacci  {
  lazy val tribs: LazyList[Int] =
    0 #:: 1 #:: 1 #:: tribs.zip(tribs.tail.zip(tribs.tail.tail)).map {case (a, (b, c)) => a + b + c}
}
