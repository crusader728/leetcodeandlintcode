package scala.crusader728.leetcode

object ShuffleArray {
  class Solution(_nums: Array[Int]) {
    import scala.util.Random
    val origin = _nums
    val rng: Random = new Random()

    /** Resets the array to its original configuration and return it. */
    def reset(): Array[Int] = {
      origin
    }

    /** Returns a random shuffling of the array. */
    def shuffle(): Array[Int] = {
      val result = Array.ofDim[Int](origin.length)
      for(i <- origin.indices) {
        result(i) = origin(i)
      }
      for(i <- origin.indices) {
        val idx = rng.nextInt(origin.length - i) + i
        val temp = result(i)
        result(i) = result(idx)
        result(idx) = temp
      }
      result
    }

  }
}
