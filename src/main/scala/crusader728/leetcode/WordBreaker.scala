package scala.crusader728.leetcode

object WordBreaker {
  case class Trie(end: Boolean, children: Map[Char, Trie])
  val empty: Trie = Trie(false, Map.empty)

  def insert(iterator: Iterator[Char], trie: Trie): Trie = {
    iterator.nextOption() match {
      case None => Trie(true, trie.children)
      case Some(ch) => Trie(trie.end, trie.children + (ch -> insert(iterator, trie.children.getOrElse(ch, empty))))
    }
  }

  val getChild: Trie => Char => Trie = t => ch => t.children.getOrElse(ch, empty)

  trait Memo[K, V] {
    def apply(f: K => V): K => V
  }

  object Memo {
    def memo[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  //dp[i] => true if s[i] ~ s[s.length -1] can be broken into words
  //dp[i] => false otherwise
  case class Solver(s: String, t: Trie) {
    val dp: Int => Boolean = Memo.mutableMapMemo(collection.mutable.HashMap.empty[Int, Boolean]) {
      case i if i >= s.length => true
      case i@_ => {

        search(i).map(dp).exists(identity)
      }
    }

    private def search(i: Int): List[Int] = {
      def helper(idx: Int, tree: Trie): List[Int] = {
        (idx, tree.end) match {
          case (n, true) if n >= s.length => List(n)
          case (n, false) if n >= s.length => Nil
          case _ =>
            val head = if(tree.end) List(idx) else Nil
            val child = getChild(tree)(s(idx))
            val tail = if(child == empty) {
              Nil
            } else {
              helper(idx + 1, child)
            }
            head ++ tail
        }
      }

      helper(i, t)
    }
  }

  def wordBreak(s: String, wordDict: List[String]): Boolean = {
    val trie = wordDict.foldLeft(empty) { case (acc, word) => insert(word.iterator, acc) }
    val solver = Solver(s, trie)
    solver.dp(0)
  }

  def main(args: Array[String]): Unit = {
    println(wordBreak("leetcode", List("leet", "code")))
  }

}
