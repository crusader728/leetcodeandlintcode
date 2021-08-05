package scala.crusader728.leetcode

object ParsingABooleanExpression {
  def parseBoolExpr(expression: String): Boolean = {
    expr.runP(expression.toList) match {
      case Fail => throw new RuntimeException
      case Succ(v, _) => v
    }
  }

  abstract class ParseResult[+A]

  case object Fail extends ParseResult[Nothing]

  case class Succ[A](a: A, remaining: List[Char]) extends ParseResult[A]

  case class Parser[A](runP: List[Char] => ParseResult[A]) {
    def map[B](f: A => B): Parser[B] =
      Parser(l => {
        runP(l) match {
          case Fail => Fail
          case Succ(a, remaining) => Succ(f(a), remaining)
        }
      })

    def flatMap[B](f: A => Parser[B]): Parser[B] =
      Parser(l => {
        runP(l) match {
          case Fail => Fail
          case Succ(a, remaining) => f(a).runP(remaining)
        }
      })
  }

  def const[A](v: A) = Parser(l => Succ(v, l))

  def fail = Parser(l => Fail)

  def or[A](p1: Parser[A], p2: Parser[A]) =
    Parser(l =>
      p1.runP(l) match {
        case Fail => p2.runP(l)
        case s@_ => s
      }
    )

  def many[A](p: Parser[A]): Parser[List[A]] = or(many1(p), const(Nil))

  def many1[A](p: Parser[A]): Parser[List[A]] =
    for {
      a <- p
      as <- many(p)
    } yield a :: as

  val ch: Parser[Char] = Parser {
    case Nil => Fail
    case x :: xs => Succ(x, xs)
  }

  def sat(p: Char => Boolean): Parser[Char] =
    for {
      c <- ch
      x <- if (p(c)) const(c) else fail
    } yield x

  val t = sat(c => c == 't').map(_ => true)
  val f = sat(c => c == 'f').map(_ => false)
  val esclationMark = sat(c => c == '!')
  val commaMark = sat(c => c == ',')
  val andMark = sat(c => c == '&')
  val orMark = sat(c => c == '|')
  val leftP = sat { c =>
    c == '('
  }
  val rightP = sat { c =>
    c == ')'
  }

  val booleanValue: Parser[Boolean] = or(t, f)

  def expr: Parser[Boolean] = or(orExpr, or(andExpr, or(notExpr, booleanValue)))

  def notExpr =
    for {
      _ <- esclationMark
      _ <- leftP
      b <- expr
      _ <- rightP
    } yield !b

  def andExpr: Parser[Boolean] =
    for {
      _ <- andMark
      _ <- leftP
      b1 <- expr
      bs <- many(for {
        _ <- commaMark
        be <- expr
      } yield be)
      _ <- rightP
      l = b1 :: bs
      v = l.foldLeft(true) { case (acc, x) => acc && x }
    } yield v

  def orExpr: Parser[Boolean] =
    for {
      _ <- orMark
      _ <- leftP
      b1 <- expr
      bs <- many(for {
        _ <- commaMark
        be <- expr
      } yield be)
      _ <- rightP
      l = b1 :: bs
      v = l.foldLeft(false) { case (acc, x) => acc || x }
    } yield v

}
