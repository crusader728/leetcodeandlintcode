package scala.crusader728.leetcode

object AddStrings {
  private case class SolutionState(carry: Int)
  private case class StateMonad[+A](run: SolutionState => (SolutionState, A)) { self =>
    def map[B](f: A => B): StateMonad[B] = StateMonad(s => {
      val (r, o) = self.run(s)
      (r, f(o))
    })

    def flatMap[B](f: A => StateMonad[B]): StateMonad[B] = StateMonad(s => {
      val (r, o) = self.run(s)
      f(o).run(r)
    })
  }

  def addStrings(num1: String, num2: String): String = {
    val (n1, n2) = if(num1.length > num2.length) {
      (num1, List.fill(num1.length - num2.length)('0').mkString ++ num2)
    } else if(num1.length < num2.length) {
      (List.fill(num2.length - num1.length)('0').mkString ++ num1, num2)
    } else {
      (num1, num2)
    }

    def step(pair: (Char, Char))(s: SolutionState): (SolutionState, Char) = {
      val d1 = (pair._1 - '0').toInt
      val d2 = (pair._2 - '0').toInt
      val sum = d1 + d2 + s.carry
      val output = sum % 10
      val carry = sum / 10
      (SolutionState(carry), ('0' + output).toChar)
    }

    def sequence(ss: List[StateMonad[Char]]): StateMonad[List[Char]] = ss match {
      case Nil => StateMonad(s => (s, Nil))
      case x :: xs => for {
        c <- x
        cs <- sequence(xs)
      } yield c :: cs
    }


    val (r, l) = sequence(n1.zip(n2).toList.map {case (c1, c2) => StateMonad(step(c1, c2))}.reverse).run(SolutionState(0))
    r.carry match {
      case 0 => l.reverse.mkString
      case i@_ => (('0' + i).toChar :: l.reverse).mkString
    }

  }
}
