package scala.crusader728.leetcode

object CourseScheduleII {
  type Vertex = Int
  type VertexCount = Int
  type Edge = (Vertex, Vertex)
  type Adj = Map[Vertex, Set[Edge]]
  type Graph = (VertexCount, Adj)

  val edgeStart: Edge => Vertex = e => e._1
  val edgeEnd: Edge => Vertex = e => e._2
  val getAdj: Graph => Adj = g => g._2
  val getNodeCounts: Graph => VertexCount = g => g._1
  val outs: Graph => Vertex => Set[Edge] = g => v => getAdj(g).getOrElse(v, Set.empty)

  def trans(g: Graph): Graph = {
    val c = getNodeCounts(g)
    val adj = getAdj(g)

    val transposed = adj.foldLeft(Map.empty[Vertex, Set[Edge]]) {case (a, (k, v)) => {
      v.foldLeft(a) { case (acc, e) => {
        val start = edgeStart(e)
        val end = edgeEnd(e)
        acc + (end -> (acc.getOrElse(end, Set.empty[Edge]) + ((end, start))))
      }}
    }}
    (c, transposed)
  }

  def vertices(g: Graph): LazyList[Vertex] = LazyList.from(0 until getNodeCounts(g))

  case class Tree(v: Vertex, children: LazyList[Tree])
  type Forest = LazyList[Tree]

  def generate(g: Graph, v: Vertex): Tree = Tree(v, LazyList.from(outs(g)(v)).map(e => generate(g, edgeEnd(e))))


  def prune(forest: Forest): Forest = {
    type State = Set[Vertex]
    def helper(f: Forest, s: State): (Forest, State) = {
      if(f.isEmpty) {
        (f, s)
      } else {
        val tree = f.head
        if(s.contains(tree.v)) {
          helper(f.tail, s)
        } else {
          val s1 = s + tree.v
          val (children, s2) = helper(tree.children, s1)
          val (tail, s3) = helper(f.tail, s2)
          (Tree(tree.v, children) #:: tail, s3)
        }
      }
    }

    helper(forest, Set.empty)._1
  }

  def dfs(g: Graph, vertices: LazyList[Vertex]): Forest = prune(vertices.map(v => generate(g, v)))

  def dff(g: Graph): Forest = dfs(g, vertices(g))
  //algorithm 1: topology sort
  def postOrder(tree: Tree): LazyList[Vertex] = postOrderF(tree.children) #::: LazyList(tree.v)
  def postOrderF(forest: Forest): LazyList[Vertex] = for {
    t <- forest
    v <- postOrder(t)
  } yield v

  def topoSort(g: Graph): LazyList[Vertex] = postOrderF(dff(g)).reverse
  //algorithm 2: strongly connected component
  def scc(g: Graph): Forest = dfs(g, postOrderF(dff(trans(g))).reverse)
  def hasCycle(graph: Graph): Boolean = scc(graph).exists(t => t.children.nonEmpty)

  //build graph
  implicit class ToGraphOps(val edges: Array[Array[Int]]) extends AnyVal {
    def toGraph(numCourses: Int): Graph = {
      val adj = edges.foldLeft(Map.empty[Vertex, Set[Edge]]) {case (acc, arr) => {
        val s = arr(1)
        val e = arr(0)
        acc + (s -> (acc.getOrElse(s, Set.empty) + ((s, e))))
      }}
      (numCourses, adj)
    }
  }
  def findOrder(numCourses: Int, prerequisites: Array[Array[Int]]): Array[Int] = {
    val graph: Graph = prerequisites.toGraph(numCourses)
    if(hasCycle(graph)) {
      Array()
    } else {
      topoSort(graph).toArray
    }
  }

  def main(args: Array[String]): Unit = {
    findOrder(2, Array(Array(1, 0)))
  }

}
