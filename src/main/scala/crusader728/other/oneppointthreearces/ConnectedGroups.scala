package scala.crusader728.other.oneppointthreearces

object ConnectedGroups {
  type Vertex = Int
  type Edge = (Vertex, Vertex)
  type Count = Int
  type Adj = Map[Vertex, Set[Edge]]
  type Graph = (Count, Adj)

  val getCount: Graph => Int = graph => graph._1
  val getAdj: Graph => Adj = graph => graph._2
  val edgeStart: Edge => Vertex = e => e._1
  val edgeEnd: Edge => Vertex = e => e._2
  val outs: Adj => Vertex => Set[Edge] = adj => v => adj.getOrElse(v, Set.empty)

  //1. to build graph
  implicit class ToGraphOps(val mat: Array[Array[Int]]) extends AnyVal {
    def toGraph: Graph = {
      val nodeCount: Count = mat.length
      val adj: Adj = (for {
        i <- mat.indices
        j <- mat(i).indices
        if mat(i)(j) == 1
      } yield (i, j))
        .foldRight(Map.empty[Vertex, Set[Edge]])(step)

      (nodeCount, adj)
    }

    private def step(cord: (Int, Int), m: Adj): Adj = {
      val s = cord._1
      val e = cord._2
      val m1 = m + (s -> (m.getOrElse(s, Set.empty[Edge]) + ((s, e))))
      m1 + (e -> (m1.getOrElse(e, Set.empty[Edge]) + ((e, s))))
    }
  }


  //2. run a connected components algorithm on the graph
  //2.1 dfs algorithm on graph, dff returns a forest of spanning trees
  case class Tree(v: Vertex, children: LazyList[Tree])
  type Forest = LazyList[Tree]

  def generate(graph: Graph, vertex: Vertex): Tree =
    Tree(vertex, LazyList.from(outs(getAdj(graph))(vertex)).map(edgeEnd).map(v => generate(graph, v)))


  def prune(f: Forest): Forest = {
    def helper(f: Forest, s: Set[Vertex]): (Set[Vertex], Forest) = {
      if(f.isEmpty) {
        (s, f)
      } else {
        val tree: Tree = f.head
        if(s.contains(tree.v)) {
          helper(f.tail, s)
        } else {
          val s1 = s + tree.v
          val (s2, children) = helper(tree.children, s1)
          val (s3, tail) = helper(f.tail, s2)
          (s3, Tree(tree.v, children) #:: tail)
        }
      }
    }

    helper(f, Set.empty)._2
  }

  def dfs(graph: Graph, vertices: LazyList[Vertex]): Forest = prune(vertices.map(v => generate(graph, v)))
  def dff(graph: Graph): Forest = dfs(graph, LazyList.from(0 until graph._1))

  def countGroups(relations: Array[Array[Int]]): Int = dff(relations.toGraph).size

  def main(args: Array[String]): Unit = {
    println(countGroups(Array(Array(1,1,0), Array(1,1,0), Array(0, 0, 1))))
  }
}
