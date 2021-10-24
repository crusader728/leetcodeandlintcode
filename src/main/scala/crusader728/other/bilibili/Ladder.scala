package scala.crusader728.other.bilibili


object Ladder {
  import scala.reflect.ClassTag

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

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

  val dp: Int => Integer = Memo.arrayMemo[Integer](11).memo {
    case 10 => Integer.valueOf(1)
    case 6 => Integer.valueOf(0)
    case 3 => Integer.valueOf(0)
    case x@_ => List(x + 1, x + 2, x + 3).filter(x => x <= 10).map(dp).foldLeft(0) { case (acc, x) => acc + x}
  }

  def main(args: Array[String]): Unit = {
    println(dp(0))
  }
}
