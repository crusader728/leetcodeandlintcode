package scala.crusader728.leetcode.pq

object KthLargestElementInArray215 {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val pq = collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)
    nums.foreach(i => {
      if(pq.size < k) {
        pq.enqueue(i)
      } else {
        if(i <= pq.head) {
          ()
        } else {
          pq.dequeue()
          pq.enqueue(i)
        }
      }
    })

    pq.head
  }
}
