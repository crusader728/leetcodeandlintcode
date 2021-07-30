package scala.crusader728.leetcode

object DecodeWaysII {
  val mod = 1000000007L
  def numDecodings(s: String): Int = {
    val memo = scala.collection.mutable.HashMap.empty[Int, Long]
    def helper(i: Int): Long = {
      memo.get(i) match {
        case Some(v) => v
        case None => {
          val result: Long = i match {
            case x if x == s.length => 1
            case x if x == s.length - 1 => if(s(i) == '0') {
              0
            } else if(s(i).isDigit) {
              1
            } else {
              9
            }
            case _ => {
              (s(i), s(i + 1)) match {
                case ('0', _) => 0
                case ('1', c) => if(c == '0') {
                  helper(i + 2)
                } else if(c.isDigit) {
                  helper(i + 1) + helper(i + 2)
                } else {
                  9 * helper(i + 2) + helper(i + 1)
                }
                case ('2', c) => if(c == '0') {
                  helper(i + 2)
                } else if('1' <= c && c <= '6') {
                  helper(i + 1) + helper(i + 2)
                } else if('7' <= c && c <= '9') {
                  helper(i + 1)
                } else {
                  6 * helper(i + 2) + helper(i + 1)
                }
                case (c, _) if c.isDigit => helper(i + 1)
                case ('*', '0') => 2 * helper(i + 2)
                case ('*', c) if '1' <= c && c <= '6' => (2 * helper(i + 2) + 9 * helper(i + 1))
                case ('*', c) if '7' <= c && c <= '9' => helper(i + 2) + 9 * helper(i + 1)
                case _ => 9 * helper(i + 1) + 15 * helper(i + 2)
              }
            }
          }
          memo += i -> (result % mod)
          result % mod
        }
      }
    }

    helper(0).toInt
  }
}
