package scala.crusader728.leetcode

object CustomSortString {
  def customSortString(order: String, str: String): String = {
    val charOrderMap = order.zipWithIndex.toMap
    val ordering = new Ordering[Char] {
      override def compare(a: Char, b: Char): Int = {
        (charOrderMap.get(a), charOrderMap.get(b)) match {
          case (None, None) => implicitly[Ordering[Char]].compare(a, b)
          case (Some(_), None) => -1
          case (None, Some(_)) => 1
          case (Some(x), Some(y)) => implicitly[Ordering[Int]].compare(x, y)
        }
      }
    }
    str.sorted(ordering)
  }
}
