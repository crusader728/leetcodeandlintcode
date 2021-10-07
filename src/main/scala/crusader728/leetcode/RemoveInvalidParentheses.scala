package scala.crusader728.leetcode

import scala.util.control.TailCalls._

object RemoveInvalidParentheses {

  def removeInvalidParentheses(s: String): List[String] = {
    val (misplacedLeft, misplacedRight) = misplacedCount(s)
    search(s.toList, misplacedLeft, misplacedRight, 0, Nil, Set.empty)
      .map(_.toList)
      .result
  }

  def misplacedCount(s: String): (Int, Int) = {
    s.foldLeft((0, 0)) { case (acc@(l, r), ch) =>
      ch match {
        case '(' => (l + 1, r)
        case ')' => if (l > 0) {
          (l - 1, r)
        } else {
          (l, r + 1)
        }
        case _ => acc
      }
    }
  }

  def search(s: List[Char],
             misplacedLeft: Int,
             misplacedRight: Int,
             counter: Int,
             result: List[Char],
             acc: Set[String]): TailRec[Set[String]] = {
    if (counter < 0) {
      done(acc)
    } else {
      s.headOption match {
        case None => if (misplacedLeft == 0 && misplacedRight == 0 && counter == 0) {
          done(acc + result.reverse.mkString)
        } else {
          done(acc)
        }
        case Some('(') => if (misplacedLeft == 0) {
          search(s.tail, misplacedLeft, misplacedRight, counter + 1, '(' :: result, acc)
        } else {
          for {
            s1 <- search(s.tail, misplacedLeft, misplacedRight, counter + 1, '(' :: result, acc)
            s2 <- search(s.tail, misplacedLeft - 1, misplacedRight, counter, result, acc)
          } yield s1 ++ s2
        }
        case Some(')') => if (misplacedRight == 0) {
          search(s.tail, misplacedLeft, misplacedRight, counter - 1, ')' :: result, acc)
        } else {
          for {
            s1 <- search(s.tail, misplacedLeft, misplacedRight, counter - 1, ')' :: result, acc)
            s2 <- search(s.tail, misplacedLeft, misplacedRight - 1, counter, result, acc)
          } yield s1 ++ s2
        }
        case Some(ch@_) => search(s.tail, misplacedLeft, misplacedRight, counter, ch :: result, acc)
      }
    }
  }

}
