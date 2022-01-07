package scala.crusader728.leetcode.backtracking

object PalindromePartitioning131 {


  def partition(s: String): List[List[String]] = {
    @annotation.tailrec
    def isPalindrome(l: Int, r: Int): Boolean = {
      if(l >= r) {
        true
      } else if(s(l) != s(r - 1)) {
        false
      } else {
        isPalindrome(l + 1, r - 1)
      }
    }

    def go(l: Int): List[List[String]] = {
      if(l >= s.length) {
        List(Nil)
      } else {
        (l + 1).to(s.length).toList
          .filter(r => isPalindrome(l, r))
          .flatMap(r => go(r).map(sol => s.substring(l, r) :: sol))
      }
    }

    go(0)
  }
}
