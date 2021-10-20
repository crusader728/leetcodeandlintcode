package scala.crusader728.leetcode


object JumpGameII {
  import scala.reflect.ClassTag

  trait Memo[@specialized(Int) K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[@specialized(Int) K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[@specialized(Int) K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.apply(f => k => m.getOrElseUpdate(k, f(k)))

    class ArrayMemo[V >: Null : ClassTag](n: Int) extends Memo[Int, V] {
      val m = new Array[V](n)
      override def memo(f: Int => V): Int => V = i => {
        if(i < 0 || i >= n) {
          f(i)
        } else {
          val t = m(i)
          if(t == null) {
            val v = f(i)
            m(i) = v
            v
          } else {
            t
          }
        }
      }
    }

    def arrayMemo[V >: Null : ClassTag](n: Int) = new ArrayMemo[V](n)
  }

  case class Solver(nums: Array[Int]) {
    val dp: Int => Option[Int] = Memo.mutableMapMemo[Int, Option[Int]](collection.mutable.HashMap.empty).memo {
      case n if n == nums.length - 1 => Some(0)
      case n if nums(n) == 0 => None
      case n@_ => (1 to nums(n)).map(i => dp(Math.min(n + i, nums.length - 1))).filter(_.isDefined).map(_.get).map(_ + 1).minOption
    }
  }
  def jump(nums: Array[Int]): Int = {
    Solver(nums).dp(0).getOrElse(-1)
  }

  def main(args: Array[String]): Unit = {
    println(jump(Array(2,3,1,1,4)))
  }
}
