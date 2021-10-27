package scala.crusader728.leetcode

object MaxAreaOfIsland {
  type Row = Int
  type Col = Int
  type Cord = (Row, Col)

  type Visited = Set[Cord]

  type Vertex = Cord
  case class Tree(v: Vertex, children: LazyList[Tree])
  type Forest = LazyList[Tree]

  val getRow: Cord => Row = cord => cord._1
  val getCol: Cord => Col = cord => cord._2

  val deltas = List((-1, 0), (1, 0), (0, -1), (0, 1))
  def outs(grid: Array[Array[Int]], vertex: Vertex): LazyList[Vertex] =
    LazyList.from(for {
      delta <- deltas
      dx = delta._1
      dy = delta._2
      newRow = dx + getRow(vertex)
      newCol = dy + getCol(vertex)
      if newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid(newRow).length && grid(newRow)(newCol) == 1
    } yield (newRow, newCol))

  def generate(grid: Array[Array[Int]], vertex: Vertex): Tree = Tree(vertex, outs(grid, vertex).map(v => generate(grid, v)))

  def prune(forest: Forest): Forest = {
    def helper(f: Forest, state: Visited): (Visited, Forest) = {
      if(f.isEmpty) {
        (state, f)
      } else {
        val tree = f.head
        if(state.contains(tree.v)) {
          helper(f.tail, state)
        } else {
          val s1 = state + tree.v
          val (s2, children) = helper(tree.children, s1)
          val (s3, tail) = helper(f.tail, s2)
          (s3, Tree(tree.v, children) #:: tail)
        }
      }
    }

    helper(forest, Set.empty)._2
  }
  def dfs(grid: Array[Array[Int]], vertex: Vertex): Forest = prune(LazyList(generate(grid, vertex)))

  def size(forest: Tree): Int = {
    1 + forest.children.map(size).sum
  }

  def nodes(tree: Tree): Visited = {
    tree.children.foldLeft(Set(tree.v)) { case (m, sub) => m ++ nodes(sub) }
  }

  def sizeOfIsland(grid: Array[Array[Int]], cord: Cord): Int = size(dfs(grid, cord).head)

  type Max = Int
  type State = (Visited, Max)

  def step(grid: Array[Array[Int]])(cord: Cord, state: State): State = {
    if(state._1.contains(cord)) {
      state
    } else {
      val island = dfs(grid, cord).head
      val area = size(island)
      val visited = nodes(island)
      if(area > state._2) {
        (visited ++ state._1, area)
      } else {
        (visited ++ state._1, state._2)
      }
    }
  }

  def maxAreaOfIsland(grid: Array[Array[Int]]): Int =
    (for {
      i <- grid.indices
      j <- grid(i).indices
      if grid(i)(j) == 1
    } yield (i, j))
    .foldRight((Set.empty[Cord], 0))(step(grid))._2
}
