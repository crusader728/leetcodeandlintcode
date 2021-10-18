package scala.crusader728.leetcode


object MaximumLengthOfSubarrayPositiveProduct {
  import scala.reflect.ClassTag

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](gen: (K => V) => K => V): Memo[K, V] = (f: K => V) => gen(f)

    private[MaximumLengthOfSubarrayPositiveProduct] class ArrayMemo[V >: Null : ClassTag](n: Int) extends Memo[Int, V] {
      lazy val m = new Array[V](n)

      override def memo(f: Int => V): Int => V = {
        case i if i < 0 || i >= n => f(i)
        case i@_ => {
          val t = m(i)
          if(t != null) {
            t
          } else {
            val v = f(i)
            m(i) = v
            v
          }
        }
      }
    }

    def arrayMemo[V >: Null : ClassTag](n: Int) = new ArrayMemo[V](n)
  }


  private case class Info(positive: Int, negative: Int)

  private case class Solver(nums: Array[Int]) {
    val dp: Int => Info = Memo.arrayMemo[Info](nums.length).memo {
      case 0 => if(nums(0) > 0) {
        Info(1, 0)
      } else if(nums(0) == 0) {
        Info(0, 0)
      } else {
        Info(0, 1)
      }
      case i@_ =>
        val prevPositive = dp(i - 1).positive
        val prevNegative = dp(i - 1).negative
        if(nums(i) > 0) {
          Info(if (prevPositive != 0) prevPositive + 1 else 1, if (prevNegative != 0) prevNegative + 1 else 0)
        } else if (nums(i) == 0) {
          Info(0, 0)
        } else {
          Info(if(prevNegative != 0) prevNegative + 1 else 0, if(prevPositive != 0) prevPositive + 1 else 1)
        }
    }
  }

  def getMaxLen(nums: Array[Int]): Int = {
    val solver = Solver(nums)
    nums.indices.map(i => solver.dp(i).positive).max
  }

  def main(args: Array[String]): Unit = {
    println(getMaxLen(Array(0,1,-2,-3,-4)))
  }
}
