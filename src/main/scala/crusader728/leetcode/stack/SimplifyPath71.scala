package scala.crusader728.leetcode.stack

object SimplifyPath71 {
  def simplifyPath(path: String): String = {
    @scala.annotation.tailrec
    def go(string: List[Char], stack: List[String]): List[String] = {
      string match {
        case Nil => stack
        case '/' :: xs => go(xs, stack)
        case _ => {
          val entry = string.takeWhile(ch => ch != '/')
          val remaining = string.dropWhile(ch => ch != '/')
          entry match {
            case '.' :: Nil => go(remaining, stack)
            case '.' :: '.' :: Nil => go(remaining, if(stack.isEmpty) stack else stack.tail)
            case _ => go(remaining, entry.mkString :: stack)
          }
        }
      }
    }

    go(path.toList, List.empty) match {
      case Nil => "/"
      case parts@_ => "/" + parts.reverse.mkString("/")
    }
  }

  def main(args: Array[String]): Unit = {
    println(simplifyPath("/../"))
  }
}
