package scala.crusader728.leetcode

object WordSearchII {
  case class Trie(terminated: Boolean, children: Map[Char, Trie])

  val empty = Trie(terminated = false, Map.empty)

  val insert: Trie => String => Trie = t => s => {
    val iterator = s.iterator
    def go(iterator: Iterator[Char], current: Trie): Trie = {
      if(iterator.hasNext) {
        val ch = iterator.next
        val child = current.children.getOrElse(ch, empty)
        val newChild = go(iterator, child)
        val newChildren = current.children + (ch -> newChild)
        Trie(current.terminated, newChildren)
      } else {
        Trie(terminated = true, current.children)
      }
    }

    go(iterator, t)
  }

  val search: Trie => Char => Trie = t => ch => t.children.getOrElse(ch, empty)

  type R = Int
  type C = Int
  type Cord = (R, C)

  val getR: Cord => R = _._1
  val getC: Cord => C = _._2

  type Board = Array[Array[Char]]
  val getChar: Board => Cord => Char = board => cord => board(getR(cord))(getC(cord))

  type Visited = Set[Cord]
  val deltas = List((-1, 0), (1, 0), (0, -1), (0, 1))

  def dfs(board: Board, cord: Cord, t: Trie, buffer: List[Char], visited: Visited): Set[String] = {
      val ch = getChar(board)(cord)
      val newVisited = visited + cord
      t.children.get(ch) match {
        case None => Set.empty
        case Some(child) => (for {
          delta <- deltas
          newR = getR(delta) + getR(cord)
          newC = getC(delta) + getC(cord)
          if newR >= 0 && newR < board.length && newC >= 0 && newC < board(newR).length && !visited.contains((newR, newC))
        } yield (newR, newC))
          .foldLeft(Set.empty[String]) { case (acc, next) => {
            acc ++ dfs(board, next, child, ch :: buffer, newVisited)
          }}.union(if(child.terminated) Set((ch :: buffer).reverse.mkString) else Set.empty)
      }
  }

  def findWords(board: Array[Array[Char]], words: Array[String]): List[String] = {
    val trie = words.foldLeft(empty) {case (acc, w) => insert(acc)(w) }
    board.indices.foldLeft(Set.empty[String]) {case (acc, i) => {
      board(i).indices.foldLeft(acc) {case (set, j) => {
        set.union(dfs(board, (i, j), trie, List.empty, Set.empty))
      }}
    }}.toList
  }

}
