package scala.crusader728.leetcode

object ReduceArraySizeToHalf {
  def minSetSize(arr: Array[Int]): Int = {
    val countMap = scala.collection.mutable.HashMap.empty[Int, Int]
    arr.foreach {i => countMap += (i -> (countMap.getOrElse(i, 0) + 1))}
    val counts = countMap.values.toList.sorted
    counts.foldRight((0, 0)) {case (count, (sum, total)) => {
      if(total >= arr.length / 2) {
        (sum, total)
      } else {
        (sum + 1, total + count)
      }
    }}._1
  }
}
