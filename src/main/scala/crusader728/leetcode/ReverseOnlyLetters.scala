package scala.crusader728.leetcode

object ReverseOnlyLetters {
  def reverseOnlyLetters(s: String): String = {
    @scala.annotation.tailrec
    def loop(l: Int, r: Int, chars: Array[Char]): Array[Char] = {
      if (l >= r) {
        chars
      } else if (!chars(l).isLetter) {
        loop(l + 1, r, chars)
      } else if (!chars(r).isLetter) {
        loop(l, r - 1, chars)
      } else {
        val temp = chars(l)
        chars(l) = chars(r)
        chars(r) = temp
        loop(l + 1, r - 1, chars)
      }
    }
    loop(0, s.length - 1, s.toCharArray).mkString
  }

}
