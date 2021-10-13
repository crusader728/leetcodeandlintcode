package scala.crusader728.leetcode

import scala.annotation.tailrec

object AsteroidCollision {
  def asteroidCollision(asteroids: Array[Int]): Array[Int] = {
    asteroids.foldRight(List.empty[Int]) {case (i, acc) =>
      if(i < 0) {
        i :: acc
      } else {

        loop(i :: acc)
      }
    }.toArray
  }

  @tailrec
  private def loop(l: List[Int]): List[Int] = l match {
    case a :: b :: xs if a > 0 && b < 0 && Math.abs(a) == Math.abs(b) => xs
    case a :: b :: xs if a > 0 && b < 0 && Math.abs(a) > Math.abs(b) => loop(a :: xs)
    case a :: b :: xs if a > 0 && b < 0 && Math.abs(a) < Math.abs(b) => b :: xs
    case _ => l
  }
}
