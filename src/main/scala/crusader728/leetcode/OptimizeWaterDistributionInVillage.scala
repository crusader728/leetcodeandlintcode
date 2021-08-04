package scala.crusader728.leetcode


object OptimizeWaterDistributionInVillage {
  type Id = Int
  type Weight = Int
  type Edge = (Id, Id, Weight)

  implicit val edgeOrdering: Ordering[Edge] = Ordering.by(e => e._3)

  def minCostToSupplyWater(n: Int, wells: Array[Int], pipes: Array[Array[Int]]): Int = {
    val wellEdges = wells.indices.foldLeft(List.empty[Edge]) {case (acc, i) => (0, i + 1, wells(i)) :: acc }
    val edges = pipes.foldLeft(wellEdges) {case (acc, edge) => (edge(0), edge(1), edge(2)) :: acc }
    val (w, _) = sequence(edges.sorted.map(step)).runS(init(0 to n))
    w.sum
  }

  private case class DisjointSet[T](group: Map[T, T])

  private def init[T](x: Seq[T]): DisjointSet[T] = DisjointSet(x.foldLeft(Map.empty[T, T]) {case (acc, e) => acc + (e -> e)})

  private def findRoot[T](a: T)(disjointSet: DisjointSet[T]): (T, DisjointSet[T]) = {
    if(a == disjointSet.group(a)) {
      (a, disjointSet)
    } else {
      val (root, newDisjointSet) = findRoot(disjointSet.group(a))(disjointSet)
      (root, DisjointSet(newDisjointSet.group + (a -> root)))
    }
  }
  private def connect[T](a: T, b: T)(disjointSet: DisjointSet[T]): (Boolean, DisjointSet[T]) = {
    val (r1, s1) = findRoot(a)(disjointSet)
    val (r2, s2) = findRoot(b)(s1)

    if(r1 == r2) {
      (false, s2)
    } else {
      (true, DisjointSet(s2.group + (r2 -> r1)))
    }
  }

  private def onEachEdge(edge: Edge)(disjointSet: DisjointSet[Id]): (Int, DisjointSet[Id]) = {
    val s = edge._1
    val e = edge._2
    val w = edge._3
    val (r, newState) = connect(s, e)(disjointSet)
    if(r) {
      (w, newState)
    } else {
      (0, newState)
    }
  }

  private trait State[S, +A] { self =>
    def runS(init: S): (A, S)
    def map[B](f: A => B): State[S, B] = (init: S) => {
      val (a, s) = self.runS(init)
      (f(a), s)
    }

    def flatMap[B](f: A => State[S, B]): State[S, B] = (init: S) => {
      val (a, s) = self.runS(init)
      f(a).runS(s)
    }
  }

  private object State {
    def apply[S, A](f: S => (A, S)): State[S, A] = (init: S) => f(init)
  }

  private def step(edge: Edge): State[DisjointSet[Id], Int] = State(onEachEdge(edge))
  private def sequence(l: List[State[DisjointSet[Id], Int]]): State[DisjointSet[Id], List[Int]] = {
    l match {
      case Nil => State(s => (Nil, s))
      case x :: xs => for {
        r <- x
        rs <- sequence(xs)
      } yield r :: rs
    }
  }
}
