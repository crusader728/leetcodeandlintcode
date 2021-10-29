package scala.crusader728.leetcode

object UniqueMorseCodeWords {
  val atoz = ('a' to 'z').toList
  val morseCodes = List(".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--..")
  val charToMorseCodes: Map[Char, String] = atoz.view.zip(morseCodes.view).foldLeft(Map.empty[Char, String]) {case (m, (ch, code)) => {
    m + (ch -> code)
  }}

  def uniqueMorseRepresentations(words: Array[String]): Int = {
    (for {
      w <- words
      morseCode = w.flatMap(ch => charToMorseCodes(ch))
    } yield morseCode)
      .distinct.length
  }
}
