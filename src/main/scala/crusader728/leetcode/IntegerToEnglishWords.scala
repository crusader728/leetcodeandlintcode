package scala.crusader728.leetcode

object IntegerToEnglishWords {
  val magnitudes = List(
    (1000000000, "Billion"),
    (1000000, "Million"),
    (1000, "Thousand"),
    (1, "")
  )

  val oneDigit: Map[Int, String] = Map(
    1 -> "One",
    2 -> "Two",
    3 -> "Three",
    4 -> "Four",
    5 -> "Five",
    6 -> "Six",
    7 -> "Seven",
    8 -> "Eight",
    9 -> "Nine"
  )

  val elevenToNineteen: Map[Int, String] = Map(
    11 -> "Eleven",
    12 -> "Twelve",
    13 -> "Thirteen",
    14 -> "Fourteen",
    15 -> "Fifteen",
    16 -> "Sixteen",
    17 -> "Seventeen",
    18 -> "Eighteen",
    19 -> "Nineteen"
  )

  val tens: Map[Int, String] = Map(
    1 -> "Ten",
    2 -> "Twenty",
    3 -> "Thirty",
    4 -> "Forty",
    5 -> "Fifty",
    6 -> "Sixty",
    7 -> "Seventy",
    8 -> "Eighty",
    9 -> "Ninety"
  )

  def threeDigitsToWords(x: Int): String = {
    val hundred = x / 100
    val ten = (x % 100) / 10
    val one = x % 10
    val a = if(hundred == 0) {
      ""
    } else {
      s"${oneDigit(hundred)} Hundred"
    }
    val b = (ten, one) match {
      case (0, 0) => ""
      case (0, _) => oneDigit(one)
      case (1, 0) => tens(ten)
      case (1, _) => elevenToNineteen(ten * 10 + one)
      case (_, 0) => tens(ten)
      case (_, _) => s"${tens(ten)} ${oneDigit(one)}"
    }

    List(a, b).filter(x => x.nonEmpty).mkString(" ")
  }



  def numberToWords(num: Int): String = {
    if(num == 0) {
      "Zero"
    } else {
      val (_, represents) = magnitudes.foldLeft((num, List.empty[(Int, String)])) {case ((current, acc), (m, s)) => {
        val q = current / m
        val r = current % m
        (r, (q, s) :: acc)
      }}
      represents
        .filter {case (n, _) => n != 0}
        .map {case (n, s) => if(s.isEmpty) {
          s"${threeDigitsToWords(n)}"
        } else {
          s"${threeDigitsToWords(n)} $s"
        }}
        .reverse
        .mkString(" ")

    }
  }
}
