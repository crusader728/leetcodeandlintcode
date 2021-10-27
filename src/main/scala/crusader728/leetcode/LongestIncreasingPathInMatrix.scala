package scala.crusader728.leetcode

object LongestIncreasingPathInMatrix {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  type Row = Int
  type Col = Int
  type Cord = (Row, Col)

  val getRow: Cord => Row = cord => cord._1
  val getCol: Cord => Col = cord => cord._2
  val deltas: List[Cord] = List((-1, 0), (1, 0), (0, 1), (0, -1))
  val add: Cord => Cord => Cord = x => y => (getRow(x) + getRow(y), getCol(x) + getCol(y))

  case class Solver(matrix: Array[Array[Int]], m: collection.mutable.HashMap[Cord, Int]) {
    val dp: Cord => Int = Memo.mutableMapMemo(m).memo { cord =>
      (for {
        delta <- deltas
        newCord = add(cord)(delta)
        newRow = getRow(newCord)
        newCol = getCol(newCord)
        if newRow >= 0 && newRow < matrix.length && newCol >=0 && newCol < matrix(newRow).length && matrix(getRow(cord))(getCol(cord)) < matrix(newRow)(newCol)
      } yield newCord)
        .map(dp)
        .maxOption
        .getOrElse(0) + 1
    }
  }

  def longestIncreasingPath(matrix: Array[Array[Int]]): Int = {
    val memo = collection.mutable.HashMap.empty[Cord, Int]
    val solver = Solver(matrix, memo)
    (for {
      i <- matrix.indices
      j <- matrix(i).indices
    } yield (i, j))
      .map(cord => solver.dp(cord))
      .max
  }
}
