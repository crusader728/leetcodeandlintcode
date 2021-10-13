package scala.crusader728.leetcode

object TwoOutOfThree {
  def twoOutOfThree(nums1: Array[Int], nums2: Array[Int], nums3: Array[Int]): List[Int] = {
    val s1 = nums1.toSet
    val s2 = nums2.toSet
    val s3 = nums3.toSet

    val resultSet = (s1 & s2) union (s1 & s3) union (s2 & s3)
    resultSet.toList
  }
}
