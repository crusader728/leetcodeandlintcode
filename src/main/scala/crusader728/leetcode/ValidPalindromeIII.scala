package scala.crusader728.leetcode

import scala.collection.mutable

object ValidPalindromeIII {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](gen: (K => V) => K => V): Memo[K, V] = (f: K => V) => gen(f)

    def mutableMemo[K, V](memo: scala.collection.mutable.HashMap[K, V]): Memo[K, V] = {
      Memo(f => k => memo.getOrElseUpdate(k, f(k)))
    }
  }

  case class Solver(str: String) {
    val solver: ((Int, Int, Int)) => Boolean = Memo.mutableMemo[(Int, Int, Int), Boolean](mutable.HashMap.empty).memo {case (l, r, k) => {
      if(k < 0) false
      else if(l >= r) true
      else if(str(l) != str(r)) {
        solver((l + 1, r, k - 1)) || solver((l, r - 1, k - 1))
      } else {
        solver((l + 1, r - 1, k))
      }
    }}
  }

  def isValidPalindrome(s: String, k: Int): Boolean = {
    Solver(s).solver(0, s.length - 1, k)
  }
}
