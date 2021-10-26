package scala.crusader728.leetcode

object MinimumDegreeOfConnectedTrioInGraph {
  type Vertex = Int
  type Edge = (Vertex, Vertex)
  type VertexCount = Int
  type Adj = Map[Vertex, Set[Edge]]
  type Graph = (VertexCount, Adj)
  type Trio = (Vertex, Vertex, Vertex)

  implicit class ToAdjOps(val edges: Array[Array[Int]]) extends AnyVal {
    def toAdj: Adj = {
      edges.foldLeft(Map.empty[Vertex, Set[Edge]]) { case (adj, arr) => {
        val start = arr(0)
        val end = arr(1)
        val adj1 = adj + (start -> (adj.getOrElse(start, Set.empty[Edge]) + ((start, end))))
        adj1 + (end -> (adj1.getOrElse(end, Set.empty[Edge]) + ((end, start))))
      }
      }
    }
  }

  def hasEdge(graph: Graph, edge: (Vertex, Vertex)): Boolean = graph._2.getOrElse(edge._1, Set.empty).contains(edge)

  def outDegree(graph: Graph, trio: Trio): Int = {
    val i = trio._1
    val j = trio._2
    val k = trio._3

    (graph._2.getOrElse(i, Set.empty) ++ graph._2.getOrElse(j, Set.empty) ++ graph._2.getOrElse(k, Set.empty) -- Set((i, j), (i, k), (j, i), (j, k), (k, i), (k, j))).size
  }


  def minTrioDegree(n: Int, edges: Array[Array[Int]]): Int = {
    val graph: Graph = (n, edges.toAdj)
    val trios = for {
      i <- 1 to n
      j <- i + 1 to n
      k <- j + 1 to n
      if hasEdge(graph, (i, j)) && hasEdge(graph, (i, k)) && hasEdge(graph, (j, k))
    } yield (i, j, k)

    val outs = trios.map(trio => outDegree(graph, trio))
    outs.minOption.getOrElse(-1)

  }
}
