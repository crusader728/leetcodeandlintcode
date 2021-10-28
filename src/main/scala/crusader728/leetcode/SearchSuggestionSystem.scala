package scala.crusader728.leetcode

object SearchSuggestionSystem {
  case class Trie(end: Boolean, children: collection.immutable.SortedMap[Char, Trie])

  val empty: Trie = Trie(false, collection.immutable.SortedMap.empty)

  def insert(iterator: Iterator[Char], trie: Trie): Trie = {
    iterator.nextOption() match {
      case None => Trie(true, trie.children)
      case Some(ch) => Trie(trie.end, trie.children + (ch -> insert(iterator, trie.children.getOrElse(ch, empty))))
    }
  }

  type Prefix = String
  type State = (Prefix, Trie)
  val getPrefix: State => Prefix = _._1
  val getTrie: State => Trie = _._2

  def toList(trie: Trie): List[String] = {
    val results = for {
      (k, child) <- trie.children.iterator
      sub <- toList(child).iterator
      result = s"$k$sub"
    } yield result
    if(trie.end) "" :: results.toList else results.toList
  }

  def suggestedProducts(products: Array[String], searchWord: String): List[List[String]] = {
    val trie = products.foldLeft(empty) {case (acc, product) => insert(product.iterator, acc) }
    val lb = collection.mutable.ListBuffer.empty[State]
    searchWord.indices.foldLeft(trie) {case (s, i) => {
      val trie = s.children.getOrElse(searchWord(i), empty)
      val prefix = searchWord.take(i + 1)
      lb.append((prefix, trie))
      trie
    }}
    (for {
      t <- lb.view
      prefix = getPrefix(t)
      trie = getTrie(t)
      result = toList(trie).map(prefix + _).take(3)
    } yield result).toList
  }
}
