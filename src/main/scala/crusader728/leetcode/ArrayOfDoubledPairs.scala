package scala.crusader728.leetcode

object ArrayOfDoubledPairs {
  def canReorderDoubled(arr: Array[Int]): Boolean = {
    val occurrence = arr.groupMapReduce(identity)(_ => 1)(_ + _)
    val keys = occurrence.keys.toList.sortBy(i => Math.abs(i))
    val (_, r) = keys.foldLeft((occurrence, true)) {case ((acc, successful), key) => {
      if(!successful) {
        (acc, successful)
      } else {
        val target = key * 2
        (acc.get(key), acc.get(target)) match {
          case (Some(c1), Some(c2)) => if(c2 < c1) {
            (acc, false)
          } else if(c2 == c1) {
            (acc - key - target, successful)
          } else {
            (acc - key + (target -> (c2 - c1)), successful)
          }
          case (None, _) => (acc, successful)
          case (_, None) => (acc, false)
        }
      }
    }}
    r
  }

}
