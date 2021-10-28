package scala.crusader728.leetcode

object WordLadder {
  type Word = String
  type Pattern = Word
  type W2P = Map[Word, Set[Pattern]]
  type P2W = Map[Pattern, Set[Word]]

  val wordToPatterns: Word => Set[Pattern] = w => Set.from(for {
    i <- w.indices
  } yield w.updated(i, '*'))

  val wordToPatternsMapping: List[Word] => Map[Word, Set[Pattern]] = l => {
    Map.from(l.map(word => (word, wordToPatterns(word))))
  }

  val patternToWords: Map[Word, Set[Pattern]] => Map[Pattern, Set[Word]] = w2p => {
    w2p.foldLeft(Map.empty[Pattern, Set[Word]]) {case (acc, (w, ps)) => {
      ps.foldLeft(acc) {case (a, p) => {
        a + (p -> (a.getOrElse(p, Set.empty) + w))
      }}
    }}
  }

  val getNeighbor: W2P => P2W => Word => Set[Word] = m1 => m2 => w => {
    for {
      p <- m1.getOrElse(w, Set.empty)
      neighbor <- m2.getOrElse(p, Set.empty)
    } yield neighbor
  }

  def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
    if(wordList.indexOf(endWord) < 0) {
      0
    } else {
      val w2p = wordToPatternsMapping(beginWord :: wordList)
      val p2w = patternToWords(w2p)
      val visited = collection.mutable.HashSet.empty[Word]
      @annotation.tailrec
      def bfs(q: Set[Word], length: Int): Int = {
        if(q.isEmpty) {
          -1
        } else {
          if(q.contains(endWord)) {
            length
          } else {
            visited.addAll(q)

            val nextLevel = for {
              w <- q
              neighbor <- getNeighbor(w2p)(p2w)(w)
              if(!visited.contains(neighbor))
            } yield neighbor
            bfs(nextLevel, length + 1)
          }
        }
      }
      bfs(Set(beginWord), 0) + 1
    }
  }

  def main(args: Array[String]): Unit = {
    println(ladderLength("hot", "dog", List("hot","dog")))
  }
}
