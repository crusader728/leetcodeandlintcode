package scala.crusader728.leetcode

object WiggleSubsequence {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElse(k, f(k)))
  }

  case class Solver(nums: Array[Int]) {
    val down: Int => Int = Memo.mutableMapMemo[Int, Int](collection.mutable.HashMap.empty[Int, Int]).memo {
      case 0 => 1
      case i@_ => if(nums(i) > nums(i - 1)) {
        down(i - 1)
      } else if(nums(i) == nums(i - 1)) {
        down(i - 1)
      } else {
        up(i - 1) + 1
      }
    }
    val up: Int => Int = Memo.mutableMapMemo[Int, Int](collection.mutable.HashMap.empty[Int, Int]).memo {
      case 0 => 1
      case i@_ => if(nums(i) > nums(i - 1)) {
        down(i - 1) + 1
      } else if(nums(i) == nums(i - 1)) {
        up(i - 1)
      } else {
        up(i - 1)
      }
    }
  }
  def wiggleMaxLength(nums: Array[Int]): Int = {
    val solver = Solver(nums)
    Math.max(solver.down(nums.length - 1), solver.up(nums.length - 1))
  }
}
