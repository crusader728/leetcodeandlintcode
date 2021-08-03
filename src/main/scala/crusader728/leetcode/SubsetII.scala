package scala.crusader728.leetcode

object SubsetII {
  def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {
    nums
      .sorted
      .foldRight(Set(List.empty[Int])) { case (i, acc) => acc.map(l => i :: l).union(acc) }
      .toList
  }
}
