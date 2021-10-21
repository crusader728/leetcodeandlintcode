package scala.crusader728.leetcode

object NumberOfProvinces {

  type VertexCount = Int
  type Vertex = Int
  type Edge = (Vertex, Vertex)
  type Adj = Map[Vertex, Set[Edge]]
  type Graph = (VertexCount, Adj)

  implicit class ToGraphOps(val matrix: Array[Array[Int]]) extends AnyVal {
    def toGraph: Graph = {
      val vertexCount: VertexCount = matrix.length
      val cords = for {
        i <- matrix.indices
        j <- matrix(i).indices
      } yield (i, j)
      val adj: Adj = cords.foldLeft(Map.empty[Vertex, Set[Edge]]) {case (acc, (x, y)) => {
        if(matrix(x)(y) == 0) {
          acc
        } else {
          val m1 = acc + (x -> ( acc.getOrElse(x, Set.empty) + Tuple2(x, y)))
          val m2 = m1 + (y -> (acc.getOrElse(y, Set.empty) + Tuple2(y, x)))
          m2
        }
      }}
      (vertexCount, adj)
    }
  }


  private def vertices(graph: Graph): LazyList[Vertex] = LazyList.range(0, graph._1)

  //dfs on graph
  //spanning tree definition
  case class Tree(v: Vertex, children: LazyList[Tree])
  //forest definition
  type Forest = LazyList[Tree]

  private def spanningTree(graph: Graph, vertex: Vertex): Tree = {
    Tree(vertex, LazyList.from(graph._2.getOrElse(vertex, LazyList.empty)).map {case (s, e) => spanningTree(graph, e)})
  }

  //prune on the spanning forest
  private def prune(forest: Forest): Forest = {
    val visited = collection.mutable.HashSet.empty[Vertex]
    def helper(f: Forest): Forest = {
      if(f.isEmpty) {
        f
      } else {
        val head = f.head
        if(visited.contains(head.v)) {
          helper(f.tail)
        } else {
          visited += head.v
          val children = helper(head.children)
          val tail = helper(f.tail)
          Tree(head.v, children) #:: tail
        }
      }
    }

    helper(forest)
  }

  //dfs returns the pruned forest
  private def dfs(graph: Graph, vs: LazyList[Vertex]): Forest = prune(vs.map(v => spanningTree(graph, v)))
  private def dff(graph: Graph): Forest = dfs(graph, LazyList.range(0, graph._1))


  def findCircleNum(isConnected: Array[Array[Int]]): Int = {
    dff(isConnected.toGraph).size
  }

  def main(args: Array[String]): Unit = {
    println(findCircleNum(Array(Array(1,1,0), Array(1,1,0), Array(0, 0, 1))))
  }
}
