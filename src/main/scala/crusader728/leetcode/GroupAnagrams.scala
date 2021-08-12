package scala.crusader728.leetcode

object GroupAnagrams {
  def groupAnagrams(strs: Array[String]): List[List[String]] = {
    strs.toList.groupBy(s => s.sorted).values.toList
  }
}
