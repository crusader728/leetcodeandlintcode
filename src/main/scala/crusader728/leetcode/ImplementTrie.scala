package scala.crusader728.leetcode

object ImplementTrie {
  class Trie() {
    private case class Trie(period: Boolean, children: Map[Char, Trie])

    private def empty: Trie = Trie(period = false, Map.empty)

    private var root = empty

    private def insert(s: Iterator[Char], t: Trie): Trie = s.nextOption match {
      case None => Trie(period = true, t.children)
      case Some(ch) =>
        val sub = t.children.getOrElse(ch, empty)
        Trie(t.period, t.children + (ch -> insert(s, sub)))
    }

    private def search(s: Iterator[Char], t: Trie, p: Trie => Boolean): Boolean = s.nextOption match {
      case None => p(t)
      case Some(ch) =>
        t.children.get(ch).exists(sub => search(s, sub, p))
    }

    def insert(word: String) {
      root = insert(word.iterator, root)
      ()
    }

    def search(word: String): Boolean = {
      search(word.iterator, root, t => t.period)
    }

    def startsWith(prefix: String): Boolean = {
      search(prefix.iterator, root, _ => true)
    }

  }
}
