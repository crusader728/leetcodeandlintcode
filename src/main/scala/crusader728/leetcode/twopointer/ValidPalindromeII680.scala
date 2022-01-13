package scala.crusader728.leetcode.twopointer

object ValidPalindromeII680 {
  def validPalindrome(s: String): Boolean = {
    @scala.annotation.tailrec
    def go(l: Int, r: Int): Boolean = {
      if(l >= r) {
        true
      } else if(s(l) == s(r)) {
        go(l + 1, r - 1)
      } else {
        palindrome(l + 1, r) || palindrome(l, r - 1)
      }
    }

    @scala.annotation.tailrec
    def palindrome(l: Int, r: Int): Boolean = {
      if(l >= r) {
        true
      } else if(s(l) != s(r)) {
        false
      } else {
        palindrome(l + 1, r - 1)
      }
    }

    go(0, s.length - 1)
  }
}
