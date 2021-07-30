package scala.crusader728.leetcode

object PushDominoes {
  def pushDominoes(dominoes: String): String = {
    case class Range(start: Int, end: Int)
    trait Direction
    case object L extends Direction
    case object R extends Direction
    case object NoDirection extends Direction

    @scala.annotation.tailrec
    def dots(l: Int, r: Int, acc: List[Range]): List[Range] = {
      if(l >= dominoes.size) {
        acc
      } else if(r >= dominoes.size) {
        Range(l, r - 1) :: acc
      }else if(dominoes(l) != '.') {
        dots(l + 1, l + 1, acc)
      } else if(dominoes(l) == '.' && dominoes(r) == '.') {
        dots(l, r + 1, acc)
      } else {
        dots(r, r, Range(l, r - 1) :: acc)
      }
    }

    @scala.annotation.tailrec
    def simulation(lf: Direction, rf: Direction, l: Int, r: Int, array: Array[Char]): Unit = {
      (lf, rf) match {
        case (R, L) => if(l < r) {
          array(l) = 'R'
          array(r) = 'L'
          simulation(lf, rf, l + 1, r - 1, array)
        } else if(l == r) {
          array(l) = '.'
        }
        case (_, L) => if(r >= l) {
          array(r) = 'L'
          simulation(lf, rf, l, r - 1, array)
        }
        case (R, _) => if(l <= r) {
          array(l) = 'R'
          simulation(lf, rf, l + 1, r, array)
        }
        case _ => for(i <- l to r) {
          array(i) = '.'
        }
      }
    }

    def toDirection(ch: Char): Direction = {
      ch match {
        case 'L' => L
        case 'R' => R
        case _ => NoDirection
      }
    }

    val ranges = dots(0, 0, Nil)
    val chars = Array.ofDim[Char](dominoes.size)
    for (i <- 0 until dominoes.size) {
      if(dominoes(i) != '.') {
        chars(i) = dominoes(i)
      }
    }
    ranges.foreach { r => {
      if(r.start == 0 && r.end == dominoes.size - 1) {
        simulation(NoDirection, NoDirection, r.start, r.end, chars)
      } else if(r.start == 0) {
        simulation(NoDirection, toDirection(dominoes(r.end + 1)), r.start, r.end, chars)
      } else if(r.end == dominoes.size - 1) {
        simulation(toDirection(dominoes(r.start - 1)), NoDirection, r.start, r.end, chars)
      } else {
        simulation(toDirection(dominoes(r.start - 1)), toDirection(dominoes(r.end + 1)), r.start, r.end, chars)
      }
    }}

    chars.mkString

  }
}
