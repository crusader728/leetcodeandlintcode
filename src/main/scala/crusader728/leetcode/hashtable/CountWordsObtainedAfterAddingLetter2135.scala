package scala.crusader728.leetcode.hashtable

object CountWordsObtainedAfterAddingLetter2135 {
  val alphabets: Set[Char] = (0 until 26).map(i => ('a'.toInt + i).toChar).toSet
  def expand(word: String): Set[String] = {
    val chs = word.toSet
    alphabets.removedAll(chs).map(ch => (ch + word).sorted)
  }

  def wordCount(startWords: Array[String], targetWords: Array[String]): Int = {
    val candidates: Set[String] = startWords.map(expand)
      .foldLeft(Set.empty[String]) { case (acc, s) => acc.union(s) }
    targetWords.foldLeft(0) {case (acc, w) => if(candidates.contains(w.sorted)) {acc + 1} else acc }
  }
}
