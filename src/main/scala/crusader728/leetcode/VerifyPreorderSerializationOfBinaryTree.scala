package scala.crusader728.leetcode

object VerifyPreorderSerializationOfBinaryTree {
  def isValidSerialization(preorder: String): Boolean = {
    @scala.annotation.tailrec
    def loop(i: Int, tokens: Seq[String], acc: List[String]): Boolean = {
      i match {
        case n if n == tokens.length =>
          acc match {
            case Nil => true
            case "#" :: Nil => true
            case _ => false
          }
        case _ =>
          val token = tokens(i)
          if(token != "#") {
            loop(i + 1, tokens, token :: acc)
          } else {
            acc match {
              case Nil => loop(i + 1, tokens,token :: acc)
              case "#" :: _ => acc.dropWhile(ch => ch == "#") match {
                case Nil => false
                case ls@_ => loop(i, tokens, ls.tail)
              }
              case _ => loop(i + 1, tokens,token :: acc)
            }
          }
      }
    }

    val tokens = preorder.split(",")
    loop(0, tokens, Nil)
  }
}
