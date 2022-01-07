package scala.crusader728.leetcode.stack

object MinimumRemoveMakeValidParentheses1249 {
  def minRemoveToMakeValid(s: String): String = {
    @scala.annotation.tailrec
    def go(i: Int, stack: List[Int], toDelete: Set[Int]): Set[Int] = {
      i match {
        case n if n == s.length => stack.foldLeft(toDelete) { case (acc, e) => acc + e }
        case n if s(n) != '(' && s(n) != ')' => go(i + 1, stack, toDelete)
        case n if s(n) == '(' => go(i + 1, i :: stack, toDelete)
        case n if s(n) == ')' => if(stack.isEmpty) {
          go(i + 1, stack, toDelete + i)
        } else {
          go(i + 1, stack.tail, toDelete)
        }
      }
    }

    val toDelete = go(0, List.empty, Set.empty)
    (0 until s.length)
      .filter(i => !toDelete.contains(i))
      .map(i => s(i))
      .mkString
  }
}
