package scala.crusader728.leetcode

object KthFactorOfN {
  @scala.annotation.tailrec
  def loop(pq: collection.mutable.PriorityQueue[Int], k: Int): Unit = {
    if(pq.size > k) {
      pq.dequeue()
      loop(pq, k)
    }
  }

  def kthFactor(n: Int, k: Int): Int = {
    val pq = collection.mutable.PriorityQueue.empty[Int]


    (1 to Math.sqrt(n).toInt)
      .filter(i => n % i == 0)
      .foreach(i => {
        if(i == n / i) {
          pq.enqueue(i)
        } else {
          pq.enqueue(i)
          pq.enqueue(n / i)
        }
        loop(pq, k)
      })

    if(pq.size == k) {
      pq.head
    } else {
      -1
    }
  }
}
