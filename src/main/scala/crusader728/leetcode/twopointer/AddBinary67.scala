package scala.crusader728.leetcode.twopointer

object AddBinary67 {
  def addBinary(a: String, b: String): String = {
    def add(v1: Int, v2: Int, carry: Boolean): (Int, Boolean) = {
      val r = v1 + v2 + (if(carry) 1 else 0)
      if(r >= 2) {
        (r - 2, true)
      } else {
        (r, false)
      }
    }

    @scala.annotation.tailrec
    def go(i: Int, j: Int, carry: Boolean, current: List[Int]): List[Int] = {
      (i, j) match {
        case (n, m) if n < 0 && m < 0 => if(carry) 1 :: current else current
        case (n, m) if n < 0 => {
          val (r, c) = add(0, b(m) - '0', carry)
          go(i - 1, j - 1, c, r :: current)
        }
        case (n, m) if m < 0 => {
          val (r, c) = add(a(n) - '0', 0, carry)
          go(i - 1, j - 1, c, r :: current)
        }
        case (_, _) => {
          val (r, c) = add(a(i) - '0', b(j) - '0', carry)
          go(i - 1, j - 1, c, r :: current)
        }
      }
    }

    go(a.length - 1, b.length - 1, false, Nil).mkString
  }
}
