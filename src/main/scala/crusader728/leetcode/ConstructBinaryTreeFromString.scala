package scala.crusader728.leetcode

object ConstructBinaryTreeFromString {
  abstract class R[+A]
  case class SUCC[A](a: A, remaining: List[Char]) extends R[A]
  case object FAIL extends R[Nothing]

  case class Parser[+A](runP: List[Char] => R[A]) {
    def map[B](f: A => B): Parser[B] = Parser(chs => {
      runP(chs) match {
        case SUCC(a, remaining) => SUCC(f(a), remaining)
        case FAIL               => FAIL
      }
    })

    def flatMap[B](f: A => Parser[B]): Parser[B] = Parser(chs => {
      runP(chs) match {
        case SUCC(a, remaining) => f(a).runP(remaining)
        case FAIL               => FAIL
      }
    })
  }

  def pure[A](a: A): Parser[A] = Parser(ls => SUCC(a, ls))
  val fail: Parser[Nothing] = Parser(_ => FAIL)
  val item: Parser[Char] = Parser {
    case Nil => FAIL
    case x :: xs => SUCC(x, xs)
  }
  def sat[A](p: Char => Boolean): Parser[Char] = for {
    x <- item
    r <- if (p(x)) pure(x) else fail
  } yield r

  val sign = sat(ch => ch == '-')
  val digit = sat(ch => ch >= '0' & ch <= '9')

  def or[A](p1: Parser[A], p2: Parser[A]): Parser[A] = Parser(l => {
    p1.runP(l) match {
      case s @ SUCC(_, _) => s
      case FAIL           => p2.runP(l)
    }
  })
  def many[A](p: Parser[A]): Parser[List[A]] = or(many1(p), pure(Nil))
  def many1[A](p: Parser[A]): Parser[List[A]] = for {
    x <- p
    xs <- many(p)
  } yield x :: xs

  val number = many1(digit).map(_.mkString.toInt)
  val lp = sat(ch => ch == '(')
  val rp = sat(ch => ch == ')')

  val neg = for {
    _ <- sign
    n <- number
  } yield (-1 * n)

  val value = or(neg, number)

  val subtree: Parser[TreeNode] = for {
    _ <- lp
    t <- tree
    _ <- rp
  } yield t

  val tree: Parser[TreeNode] = for {
    n <- value
    subtrees <- many(subtree)
    (l, r) = subtrees match {
      case Nil           => (null, null)
      case x :: Nil      => (x, null)
      case x :: y :: Nil => (x, y)
      case _             => throw new RuntimeException
    }
  } yield new TreeNode(n, l, r)

  def str2tree(s: String): TreeNode = {
    tree
      .runP(s.toList) match {
      case SUCC(t, r) => t
      case FAIL => null
    }
  }
}
