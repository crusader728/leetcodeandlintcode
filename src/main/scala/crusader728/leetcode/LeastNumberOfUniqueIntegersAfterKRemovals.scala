package scala.crusader728.leetcode

object LeastNumberOfUniqueIntegersAfterKRemovals {
  def findLeastNumOfUniqueInts(arr: Array[Int], k: Int): Int = {
    @scala.annotation.tailrec
    def loop(counts: List[(Int, Int)], x: Int): List[(Int, Int)] = {
      println(counts, x)
      counts match {
        case Nil => Nil
        case (_, c) :: cs => if(c > x) {
          counts
        } else if(c == x) {
          cs
        } else {
          loop(cs, x - c)
        }
      }
    }

    val counts = arr
      .groupMapReduce(identity)(_ => 1)(_ + _)
      .toList
      .sortBy {case (v, c) => c}

    loop(counts, k).size
  }
}
