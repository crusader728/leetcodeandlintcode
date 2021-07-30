package scala.crusader728.leetcode

object ThreeSumClosest {
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val sorted = nums.sorted

    @scala.annotation.tailrec
    def loop(base: Int, l: Int, r: Int, acc: Int): Int = {
      if(l >= r) {
        acc
      } else {
        val sum = base + sorted(l) + sorted(r)
        println(base, l, r, sum)
        val min = if(Math.abs(sum - target) < Math.abs(acc - target)) {
          sum
        } else {
          acc
        }
        if(sum > target) {
          loop(base, l, r - 1, min)
        } else {
          loop(base, l + 1, r, min)
        }
      }
    }

    sorted.indices.tail.foldLeft(
      loop(sorted(0), 1, sorted.size - 1, sorted(0) + sorted(1) + sorted(sorted.size - 1))
    ) {case (acc, i) => {
      if(i >= sorted.size - 2) {
        acc
      } else {
        loop(sorted(i), i + 1, sorted.size - 1, acc)
      }
    }}
  }
}
