package scala.crusader728.leetcode

object CourseSchedule {
  type LL[A] = LazyList[A]
  type V = Int
  type E = (V, V)
  type Adj = Map[V, Set[E]]
  type VBound = Int
  type G = (VBound, Adj)

  val edgeStart: E => V = _._1
  val edgeEnd: E => V = _._2
  val getAdj: G => Adj = _._2
  val getVbound: G => VBound = _._1

  val outs: G => V => LL[E] = g => v => LazyList.from(getAdj(g).getOrElse(v, Set.empty))
  val edges: G => LL[E] = g => LazyList.from(getAdj(g).values).flatMap(s => LazyList.from(s))
  val reverse: E => E = _.swap
  val buildGraph: VBound => LL[E] => G = bound => edges => {
    val adj = edges.foldLeft(Map.empty[V, Set[E]]) { case (acc, (s, e)) => {
      acc + (s -> (acc.getOrElse(s, Set.empty) + ((s, e))))
    }}
    (bound, adj)
  }

  val transpose: G => G = g => buildGraph(getVbound(g))(edges(g).map(reverse))

  //1. dfs
  case class Tree(v: V, children: LL[Tree])
  type Forest = LL[Tree]
  val generate: G => V => Tree = g => v => Tree(v, outs(g)(v).map(edgeEnd).map(x => generate(g)(x)))
  val prune: Forest => Forest = forest => {
    val visited = collection.mutable.Set.empty[V]
    def helper(f: Forest): Forest = {
      if(f.isEmpty) {
        f
      } else {
        val tree = f.head
        if(visited.contains(tree.v)) {
          helper(f.tail)
        } else {
          visited += tree.v
          val children = helper(tree.children)
          val tail = helper(f.tail)
          Tree(tree.v, children) #:: tail
        }
      }
    }

    helper(forest)
  }
  val dfs: G => LL[V] => Forest = g => vs => prune(vs.map(generate(g)(_)))
  val dff: G => Forest = g => dfs(g)(LazyList.from(0 until getVbound(g)))
  val postOrder: Tree => LL[V] = t => postOrderF(t.children) #::: LazyList(t.v)
  val postOrderF: Forest => LL[V] = f => f.flatMap(postOrder)
  val printTree: Tree => String = t => s"Tree(${t.v}, ${printF(t.children)})"
  val printF: Forest => String = f => f.map(printTree).mkString(",")
  val toposort: G => LL[V] = g => postOrderF(dff(g)).reverse
  //2. strongly connected component algorithm
  val scc: G => Forest = g => dfs(g)(postOrderF(dff(transpose(g))).reverse)
  val hasCycle: G => Boolean = g => scc(g)exists(t => t.children.nonEmpty)


  val hasSelfEdges: G => Boolean = g => edges(g).exists(e => edgeEnd(e) == edgeStart(e))

  implicit class ToGraphOps(val prerequisites: Array[Array[Int]]) extends AnyVal {
    def toGraph(num: Int): G = {
      val bound = num
      val edges = LazyList.from(prerequisites)
        .map(arr => (arr(0), arr(1)))
      buildGraph(bound)(edges)
    }
  }

  def canFinish(numCourses: Int, prerequisites: Array[Array[Int]]): Boolean = {
    val g = prerequisites.toGraph(numCourses)
    !hasCycle(g) && !hasSelfEdges(g)
  }

  def main(args: Array[String]): Unit = {
    canFinish(20,
      Array(Array(0,10),Array(3,18),Array(5,5),Array(6,11),Array(11,14),Array(13,1),Array(15,1),Array(17,4)))
  }
}
