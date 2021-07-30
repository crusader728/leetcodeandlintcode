package scala.crusader728.leetcode

object IsomorphicStrings {
  def isIsomorphic(s: String, t: String): Boolean = {
    val mapping = scala.collection.mutable.HashMap.empty[Char, Char]
    val reverse = scala.collection.mutable.HashMap.empty[Char, Char]
    @scala.annotation.tailrec
    def go(i: Int): Boolean = {
      i match {
        case n if n == s.length => true
        case _ => {
          val ch1 = s(i)
          val ch2 = t(i)
          if(!mapping.contains(ch1)) {
            mapping += ch1 -> ch2
          }
          if(!reverse.contains(ch2)) {
            reverse += ch2 -> ch1
          }
          if(mapping(ch1) == ch2 && reverse(ch2) == ch1) {
            go(i + 1)
          } else {
            false
          }
        }
      }
    }
    go(0)
  }
}
