package scala.crusader728.other.bilibili

object GameOf30 {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo.apply(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(m1: collection.mutable.HashMap[Int, Boolean]) {
    val dp: Int => Boolean = Memo.mutableMapMemo[Int, Boolean](m1).memo {
      case n if n >= 30 => false
      case n@_ if n + 3 >= 30 => true
      case n@_ =>
        (for {
          i <- List(n + 1, n + 2, n + 3).filter(i => !dp(i))
          j <- List(1, 2, 3)
        } yield (i + j)).exists(x => dp(x))
    }
  }

  def main(args: Array[String]): Unit = {
    val m1 = collection.mutable.HashMap.empty[Int, Boolean]
    if(Solver(m1).dp(0)) {
      println(List(1,2,3).filter(i => !m1(i)).head)
    }
  }
}
