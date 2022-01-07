package scala.crusader728.leetcode.pq

object CarPooling1094 {
  type TS = Int
  type CNT = Int
  type Info = (CNT, TS, TS)

  val toInfo: Array[Int] => Info = array => (array(0), array(1), array(2))
  val getStart: Info => TS = _._2
  val getEnd: Info => TS = _._3
  val getCnt: Info => CNT = _._1

  val infoOrderByStart: Ordering[Info] = Ordering.by(info => getStart(info))
  val infoOrderByEnd: Ordering[Info] = Ordering.by(info => getEnd(info))

  def carPooling(trips: Array[Array[Int]], capacity: Int): Boolean = {
    val sortedTrips = trips.map(toInfo)
      .sorted(infoOrderByStart)
    val pq = collection.mutable.PriorityQueue.empty[Info](infoOrderByEnd.reverse)

    @scala.annotation.tailrec
    def loop(i: Int, currentCnt: Int): Boolean = {
      if (i == sortedTrips.length) {
        true
      } else if (pq.isEmpty || getEnd(pq.head) > getStart(sortedTrips(i))) {
        pq.enqueue(sortedTrips(i))
        if (currentCnt + getCnt(sortedTrips(i)) > capacity) {
          false
        } else {
          loop(i + 1, currentCnt + getCnt(sortedTrips(i)))
        }
      } else {
        val top = pq.dequeue()
        loop(i, currentCnt - getCnt(top))
      }
    }


    loop(0, 0)
  }

  def main(args: Array[String]): Unit = {
    println(carPooling(Array(Array(2,1,5), Array(3,3,7)), 5))
  }
}
