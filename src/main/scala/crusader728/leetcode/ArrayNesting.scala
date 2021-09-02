package scala.crusader728.leetcode

object ArrayNesting {
  def arrayNesting(nums: Array[Int]): Int = {
    @scala.annotation.tailrec
    def helper(q: Option[Int], total: Set[Int], visited: Set[Int], max: Int): Int = {
      if (q.isEmpty && total.isEmpty) {
        Math.max(visited.size, max)
      } else if (q.isEmpty) {
        helper(Some(total.head), total, Set.empty, Math.max(visited.size, max))
      } else {
        val (newTotal, newVisited) = q.foldLeft((total, visited)) { case ((t, v), i) => (t - i, v + i) }
        val newQ = q.map(i => nums(i)).filter(i => !newVisited.contains(i))
        helper(newQ, newTotal, newVisited, max)
      }
    }
    val total = (0 until nums.length - 1).toSet
    helper(Some(nums(0)), total, Set.empty, Int.MinValue)
  }
}
