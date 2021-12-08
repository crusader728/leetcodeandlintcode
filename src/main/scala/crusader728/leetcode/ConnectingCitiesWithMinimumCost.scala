package scala.crusader728.leetcode

object ConnectingCitiesWithMinimumCost {
  type City = Int
  type Cost = Int
  type Edge = (City, City, Cost)

  val getStart: Edge => City = _._1
  val getEnd: Edge => City = _._2
  val getCost: Edge => Cost = _._3

  type Adj = Map[City, List[Edge]]

  implicit val edgeOrdering: Ordering[Edge] = Ordering.by(getCost)


  def minimumCost(n: Int, connections: Array[Array[Int]]): Int = {
    val pq = collection.mutable.PriorityQueue.empty[Edge]
    val adj = connections.foldLeft(Map.empty[City, List[Edge]]) {case (acc, arr) => {
      val edge = (arr(0), arr(1), arr(2))
      val acc1 = acc + (arr(0) ->
        (
          edge :: acc.getOrElse(arr(0), List.empty)
        ))
      val acc2 = acc1 + (
        arr(1) ->
          (edge :: acc1.getOrElse(arr(1), List.empty))
      )
      acc2
    }}

    val initial = adj.head
    val visited = collection.mutable.HashSet(adj.head._1)
    initial._2.foreach { e => pq.enqueue(e) }
    @scala.annotation.tailrec
    def go(acc: Cost): Cost = {
      if(visited.size == n) {
        acc
      } else if(pq.isEmpty) {
        acc
      } else {
        val top = pq.dequeue()
        if(visited.contains(getStart(top)) && visited.contains(getEnd(top))) {
          go(acc)
        } else if(visited.contains(getStart(top))) {
          visited += getEnd(top)
          adj.getOrElse(getEnd(top), List.empty).foreach(e => pq.enqueue(e))
          go(acc + getCost(top))
        } else {
          visited += getStart(top)
          adj.getOrElse(getStart(top), List.empty).foreach(e => pq.enqueue(e))
          go(acc + getCost(top))
        }
      }
    }

    val cost = go(0)
    if(visited.size != n) {
      -1
    } else {
      cost
    }
  }
}
