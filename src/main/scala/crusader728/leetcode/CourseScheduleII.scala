package scala.crusader728.leetcode

object CourseScheduleII {
  type Vertex = Int
  type Edge = (Int, Int)
  type Graph = (Int, Map[Vertex, List[Edge]])
  type State = Set[Vertex]

  case class Tree(v: Int, children: LazyList[Tree])
  type Forest = LazyList[Tree]


  private def buildGraph(n: Int, edges: Seq[(Int, Int)]): Graph = {
    val adj = edges.foldRight(Map.empty[Vertex, List[Edge]]) {case (arr, acc) =>
      val start = arr._1
      val end = arr._2
      val newL = acc.getOrElse(start, List.empty)
      acc + (start -> ((start, end) :: newL))
    }
    val result = (0 until n).foldRight(adj) { case (i, acc) => {
      if(acc.contains(i)) acc else acc + (i -> List.empty)
    }}
    (n, result)
  }

  def vertices(g: Graph): LazyList[Vertex] = LazyList.from(g._2.keys)

  def chop(forest: Forest): Forest = {
    def helper(f: Forest, s: Set[Int]): (Set[Int], Forest) = f match {
      case x if x.isEmpty => (s, x)
      case x@_ =>
        val head = x.head
        val v = head.v
        val children = head.children
        val ts = x.tail
        if(s.contains(v)) {
          helper(ts, s)
        } else {
          val s1 = s + v
          val (s2, subs) = helper(children, s1)
          val (s3, tss) = helper(ts, s2)
          (s3, Tree(v, subs) #:: tss)
        }
    }

    helper(forest, Set.empty)._2
  }

  def edges(graph: Graph): List[Edge] = {
    graph._2.values.foldRight(List.empty[Edge]) {case (l, acc) => l ++ acc}
  }

  def reverseEdge(edges: List[Edge]): List[Edge] = {
    edges.map {_.swap}
  }

  def transpose(graph: Graph): Graph = buildGraph(graph._1, reverseEdge(edges(graph)))

  private def generate(graph: Graph, vertex: Vertex): Tree = {
    Tree(vertex, LazyList.from(graph._2.getOrElse(vertex, List.empty)).map {case (_, e) => generate(graph, e) })
  }

  private def dfs(graph: Graph, vs: LazyList[Vertex]): Forest = chop(vs.map(v => generate(graph, v)))

  private def dff(graph: Graph): Forest = dfs(graph, vertices(graph))

  def preorderF(children: LazyList[Tree]): LazyList[Vertex] = children.flatMap(preorder)

  def preorder(tree: Tree): LazyList[Vertex] = tree.v #:: preorderF(tree.children)

  def postOrderF(children: LazyList[Tree]): LazyList[Vertex] = children.flatMap(postOrder)

  def postOrder(tree: Tree): LazyList[Vertex] = postOrderF(tree.children) #::: LazyList(tree.v)

  def scc(graph: Graph): Forest = {
    dfs(graph, postOrderF(dff(transpose(graph))).reverse)
  }

  def topSort(g: Graph): LazyList[Vertex] = postOrderF(dff(g)).reverse

  def findOrder(numCourses: Int, prerequisites: Array[Array[Int]]): Array[Int] = {
    val graph = buildGraph(numCourses, prerequisites.map(arr => (arr(0), arr(1))))
    val hasCycle = scc(graph).exists(t => t.children.nonEmpty)
    if(hasCycle) {
      Array()
    } else {
      topSort(graph).reverse.toArray
    }
  }


}
