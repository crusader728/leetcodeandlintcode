package scala.crusader728.leetcode

object ReverseWordsInString {
  def reverseWords(s: String): String = {
    s
      .trim
      .split("\\s+")
      .reverse
      .mkString(" ")

  }
}
