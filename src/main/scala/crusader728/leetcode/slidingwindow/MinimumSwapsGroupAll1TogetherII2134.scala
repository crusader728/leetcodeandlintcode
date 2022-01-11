package scala.crusader728.leetcode.slidingwindow

object MinimumSwapsGroupAll1TogetherII2134 {
  def minSwaps(nums: Array[Int]): Int = {
    val ones = nums.count(i => i == 1)
    if(ones == 0) {
      0
    } else {
      val n = nums.length
      var min = Int.MaxValue
      val q = collection.mutable.Queue.empty[Int]
      (0 until 2 * n).foreach { i => {
        if (i < ones) {
          if (nums(i % n) == 0) {
            q.enqueue(i)
            min = q.size
          }
        } else {
          q.dequeueWhile(idx => i - idx >= ones)
          if (nums(i % n) == 0) {
            q.enqueue(i)
          }
          min = Math.min(q.size, min)
        }
      }}
      min
    }
  }

  def main(args: Array[String]): Unit = {
    println(minSwaps(Array(0,1,0,1,1,0,0)))
  }
}
