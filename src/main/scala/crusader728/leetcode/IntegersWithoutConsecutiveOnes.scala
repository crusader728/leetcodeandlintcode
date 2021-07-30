package scala.crusader728.leetcode

object IntegersWithoutConsecutiveOnes {
  def findIntegers(n: Int): Int = {
    val aux = Array.ofDim[Int](32)
    for(i <- 0 until 32) {
      aux(i) = i match {
        case 0 => 1
        case 1 => 2
        case _ => aux(i - 1) + aux(i - 2)
      }
    }

    @scala.annotation.tailrec
    def loop(i: Int, isPrevBitOne: Boolean, acc: Int): Int = {
      if(i < 0) {
        acc
      } else {
        val leadingBit = n & (1 << i)
        if(leadingBit == 0) {
          loop(i - 1, false, acc)
        } else if(isPrevBitOne) {
          acc + aux(i) - 1
        } else {
          loop(i - 1, true, acc + aux(i))
        }
      }
    }

    loop(30, false, 0) + 1
  }
}
