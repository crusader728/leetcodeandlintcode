package scala.crusader728.leetcode

object ReverseWordInStringII {
  def reverse[T](arr: Array[T], left: Int, right: Int): Unit = {
    @scala.annotation.tailrec
    def go(l: Int, r: Int): Unit = {
      if(l <= r) {
        val temp = arr(l)
        arr(l) = arr(r)
        arr(r) = temp
        go(l + 1, r - 1)
      }
    }
    go(left, right)
  }

  def reverseWords(s: Array[Char]): Unit = {
    @scala.annotation.tailrec
    def go(start: Int, end: Int): Unit = {
      if(start >= s.length) {
        ()
      } else if(end != s.length && s(end) != ' ') {
        go(start, end + 1)
      } else {
        reverse(s, start, end - 1)
        go(end + 1, end + 1)
      }
    }

    reverse(s, 0, s.length - 1)
    go(0, 0)
  }
}
