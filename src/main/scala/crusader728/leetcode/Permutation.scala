package scala.crusader728.leetcode

object Permutation {
  import scala.util.control.TailCalls._

  def permute(nums: Array[Int]): List[List[Int]] = {
    nums.toList.foldRight(done(List(List.empty[Int]))) {case (e, acc) => for {
      xss <- acc
      r <- sequence(xss.map(xs => insert(e, xs))).map(l => l.flatten)
    } yield r }
      .result
  }

  private def sequence[A](x: List[TailRec[A]]): TailRec[List[A]] = x match {
    case Nil => done(Nil)
    case tc :: tcs => for {
      a <- tc
      as <- sequence(tcs)
    } yield a :: as
  }

  private def insert(x: Int, xs: List[Int]): TailRec[List[List[Int]]] = xs match {
    case Nil => done(List(List(x)))
    case y :: ys => (for {
      tail <- insert(x, ys)
      sol = tail.map(l => y :: l)
    } yield sol)
      .map(r => (x :: xs) :: r)
  }

  def main(args: Array[String]): Unit = {
    println(permute(Array(1,2,3)))
  }
}
