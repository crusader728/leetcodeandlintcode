package scala.crusader728.leetcode

object MaximumProfitJobScheduling {
  type TS = Int
  type Profit = Int
  type Job = (TS, TS, Profit)

  val start: Job => TS = job => job._1
  val end: Job => TS = job => job._2
  val profit: Job => Profit = job => job._3

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(jobs: Array[Job]) {

    private def nextJobIdx(ts: TS): Int = {
      @scala.annotation.tailrec
      def helper(l: Int, r: Int): Int = {
        if(l >= r) {
          l
        } else {
          val mid = l + (r - l) / 2
          val probe = start(jobs(mid))
          if(probe < ts) {
            helper(mid + 1, r)
          } else {
            helper(l, mid)
          }
        }
      }

      helper(0, jobs.length)
    }

    val dp: Int => Profit = Memo.mutableMapMemo(collection.mutable.HashMap.empty[Int, Profit]).memo {
      case i if i >= jobs.length => 0
      case i@_ => {
        val nopick = dp(i + 1)
        val pick = profit(jobs(i)) + dp(nextJobIdx(end(jobs(i))))
        Math.max(nopick, pick)
      }
    }
  }

  //search with memorization
  //when we are looking at the ith job
  //we can either pick it nor not
  //if we not pick it, the maximum profit is -> dp(i + 1)
  //otherwise, profit of the job + dp(next_available job)
  //and the result is the maximum of the two option

  def jobScheduling(startTime: Array[Int], endTime: Array[Int], profit: Array[Int]): Int = {
    val jobs: Array[Job] = startTime.view.zip(endTime.view.zip(profit.view))
      .map { case (s, (e, p)) => (s, e, p) }
      .toArray
      .sortBy(job => start(job))
    Solver(jobs).dp(0)
  }
}
