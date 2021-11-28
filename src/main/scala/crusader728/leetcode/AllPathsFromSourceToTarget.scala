package scala.crusader728.leetcode

object AllPathsFromSourceToTarget {
  type Vertex = Int
  type Edge = (Vertex, Vertex)
  type Adj = Map[Vertex, Set[Edge]]
  type VertexCount = Int
  type Graph = (VertexCount, Adj)

  val start: Edge => Vertex = _._1
  val end: Edge => Vertex = _._2
  val neighbours: Adj => Vertex => Set[Vertex] = adj => n => adj.getOrElse(n, Set.empty).map(end)
  val addEdge: Adj => Edge => Adj = adj => edge => {
    val s = start(edge)
    val e = end(edge)
    adj + (s -> (adj.getOrElse(s, Set.empty) + edge))
  }
  val bound: Graph => VertexCount = _._1
  val adj: Graph => Adj = _._2
  val buildgraph: Array[Array[Int]] => Graph = arr => {
    val bound = arr.length
    val edges = for {
      i <- arr.indices
      j <- arr(i)
    } yield (i, j)
    val adj = edges.foldLeft(Map.empty[Vertex, Set[Edge]]) {case (acc, e) => {
      addEdge(acc)(e)
    }}
    (bound, adj)
  }


  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.apply(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(g: Graph) {
    def dp: Int => List[List[Int]] = Memo.mutableMapMemo(collection.mutable.HashMap.empty[Int, List[List[Int]]]).memo {
      case v if v == bound(g) - 1 => List(List(v))
      case v@_ =>
        val next = neighbours(adj(g))(v)
        next.map(n => dp(n))
        .fold(Nil)(_ ++ _)
        .map(sol => v :: sol)
    }
  }

  def allPathsSourceTarget(graph: Array[Array[Int]]): List[List[Int]] = {
    val solver = Solver(buildgraph(graph))
    solver.dp(0)
  }
}
