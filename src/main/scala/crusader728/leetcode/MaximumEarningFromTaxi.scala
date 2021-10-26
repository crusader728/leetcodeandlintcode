package scala.crusader728.leetcode

object MaximumEarningFromTaxi {
  type Ride = Array[Int]
  type Point = Int
  type Tip = Int
  type Money = Long
  type Idx = Int

  val start: Ride => Point = arr => arr(0)
  val end: Ride => Point = arr => arr(1)
  val tip: Ride => Tip = arr => arr(2)
  val earning: Ride => Money = ride => end(ride) - start(ride) + tip(ride)

  def underbound(rides: Array[Ride], p: Point): Idx = {
    @scala.annotation.tailrec
    def helper(l: Idx, r: Idx, target: Point): Idx = {
      if(l >= r) {
        l
      } else {
        val mid = l + (r - l) / 2
        val ride = rides(mid)
        val s = start(ride)
        if(s < target) {
          helper(mid + 1, r, target)
        } else {
          helper(l, mid, target)
        }
      }
    }

    helper(0, rides.length, p)
  }

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(n: Int, rides: Array[Ride]) {
    val dp: Idx => Money = Memo.mutableMapMemo(collection.mutable.HashMap.empty[Idx, Money]).memo {
      case i if i >= rides.length => 0L
      case i@_ => {
        val nopick = dp(i + 1)
        val earn = earning(rides(i))
        val next = underbound(rides, end(rides(i)))
        val pick = earn + dp(next)
        Math.max(pick, nopick)
      }
    }
  }

  //1. sorted the array based on start points
  //2. dp(i) means the maximum earning from i to length(rides)
  //3. dp(i) = Math.max(
  //3.1     dp(i + 1), <- i don't pick this passenger
  //3.2     earning(rides(i)) + dp(next) <- pick this passenger
  //   )
  def maxTaxiEarnings(n: Int, rides: Array[Array[Int]]): Long = Solver(n, rides.sortBy(ride => start(ride))).dp(0)
}
