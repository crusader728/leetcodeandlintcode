package scala.crusader728.leetcode

object Subsets {
  def subsets(nums: Array[Int]): List[List[Int]] = {
    nums
      .sorted
      .foldRight(Set(List.empty[Int])) {case (i, acc) => acc.map(l => i :: l) ++ acc }
      .toList
  }
}
