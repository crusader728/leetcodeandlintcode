package scala.crusader728.leetcode

object NumberOfValidWordsInASentence {
  type WordValidation = String => Boolean

  val noDigits: WordValidation = (w: String) => w.count(ch => ch.isDigit) == 0
  val hyphenSurrounded: WordValidation = w => {
    val hyphens = w.indices.filter(i => w(i) == '-')
    if(hyphens.isEmpty) {
      true
    } else if(hyphens.size > 1) {
      false
    } else {
      val idx = hyphens.head
      if(idx == 0 || idx == w.length - 1) {
        false
      } else {
        w(idx - 1).isLetter && w(idx + 1).isLetter
      }
    }
  }
  def punctuation(punc: Char): WordValidation = w => {
    val idx = w.indexOf(punc)
    if(idx < 0){
      true
    } else if(idx == w.length - 1) {
      true
    } else {
      false
    }
  }

  val validations = List(noDigits, hyphenSurrounded, punctuation('.'), punctuation('!'), punctuation(',') )

  def countValidWords(sentence: String): Int = {
    sentence
      .trim
      .replaceAll("\\s+", " ")
      .split(" ")
      .count(w => {
        validations.map(f => f(w)).forall(identity)
      })

  }
}
