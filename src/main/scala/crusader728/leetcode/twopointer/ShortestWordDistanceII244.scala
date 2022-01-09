package scala.crusader728.leetcode.twopointer


object ShortestWordDistanceII244 {

  class WordDistance(_wordsDict: Array[String]) {
    val storage: Map[String, List[Int]] =
      _wordsDict.indices.foldRight(Map.empty[String, List[Int]]) {case (i, acc) => {
        val word = _wordsDict(i)
        acc + (word -> (i :: acc.getOrElse(word, List.empty)))
      }}

    def shortest(word1: String, word2: String): Int = {
      val s1 = storage.getOrElse(word1, List.empty)
      val s2 = storage.getOrElse(word2, List.empty)

      @scala.annotation.tailrec
      def go(current: Int, l1: List[Int], l2: List[Int]): Int = {
        if(l1.isEmpty || l2.isEmpty) {
          current
        } else {
          val h1 = l1.head
          val h2 = l2.head
          if(h1 >= h2) {
            go(Math.min(current, h1 - h2), l1, l2.tail)
          } else {
            go(Math.min(current, h2 - h1), l1.tail, l2)
          }
        }
      }

      go(Int.MaxValue, s1, s2)
    }

  }
}
