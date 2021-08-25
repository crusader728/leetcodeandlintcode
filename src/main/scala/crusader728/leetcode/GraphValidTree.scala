package scala.crusader728.leetcode


object GraphValidTree {
  type Graph = Map[Int, Set[Int]]

  private implicit class GraphOps(val edges: Array[Array[Int]]) extends AnyVal {
    def toGraph: Graph = edges.foldLeft(Map.empty[Int, Set[Int]]) { case (acc, edge) => {
      val start = edge(0)
      val end = edge(1)
      val m1 = acc + (start -> (acc.getOrElse(start, Set.empty[Int]) + end))
      m1 + (end -> (m1.getOrElse(end, Set.empty[Int]) + start))
    }
    }
  }

  def validTree(n: Int, edges: Array[Array[Int]]): Boolean = {
    if (n != edges.length + 1) {
      false
    } else if (edges.length == 0) {
      n == 1
    } else {
      val graph = edges.toGraph
      val visited = scala.collection.mutable.HashSet.empty[Int]

      @scala.annotation.tailrec
      def helper(q: List[Int]): Boolean = {
        q.foreach(n => visited += n)
        q match {
          case Nil => n == visited.size
          case _ =>
            val next = for {
              n <- q
              neighbor <- graph.getOrElse(n, Set.empty[Int]).diff(visited)
            } yield neighbor
            helper(next)
        }
      }

      helper(List(0))
    }
  }
}
