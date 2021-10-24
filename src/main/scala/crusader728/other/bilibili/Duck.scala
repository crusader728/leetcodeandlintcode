package scala.crusader728.other.bilibili

object Duck {
  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)

    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo[K, V](f => k => m.getOrElseUpdate(k, f(k)))
  }

  sealed abstract class FoodIngredient extends Product with Serializable {
    def size: Int
    def value: Int
  }

  case class Duck(size: Int, value: Int) extends FoodIngredient
  case class Cucumber(size: Int, value: Int) extends FoodIngredient

  case class Solver(maxSize: Int, fillings: Array[FoodIngredient]) {
    val dp: ((Int, Int, Boolean, Boolean)) => Int = Memo.mutableMapMemo[(Int, Int, Boolean, Boolean), Int](collection.mutable.HashMap.empty).memo {
      case (n, _, true, true) if n >= fillings.length => 0
      case (n, _, _, _) if n >= fillings.length => -1
      case (n, w, hasDuck@_, hasCucumber@_) => fillings(n) match {
        case Duck(s, v) => if(w + s > maxSize) {
          dp(n + 1, w, hasDuck, hasCucumber)
        } else {
          Math.max(
            dp(n + 1, w + s, true, hasCucumber) + v,
            dp(n + 1, w, hasDuck, hasCucumber)
          )
        }
        case Cucumber(s, v) => if(w + s > maxSize) {
          dp(n + 1, w, hasDuck, hasCucumber)
        } else {
          Math.max(
            dp(n + 1, w + s, hasDuck, true) + v,
            dp(n + 1, w, hasDuck, hasCucumber)
          )
        }
      }
    }
  }

  def maxValue(maxSize: Int,
               duckSize: Array[Int],
               duckScore: Array[Int],
               cucumberSize: Array[Int],
               cucumberScore: Array[Int]): Int = {
    val ducks = duckSize.view.zip(duckScore.view).map(t => Duck(t._1, t._2))
    val cucumbers = cucumberSize.view.zip(cucumberScore.view).map(t => Cucumber(t._1, t._2))

    Solver(maxSize, (ducks ++ cucumbers).toArray).dp(0, 0, false, false)

  }

  def main(args: Array[String]): Unit = {
    println(
      maxValue(
        500,
        Array(85, 86, 73, 66, 114, 51, 99),
        Array(71, 103, 44, 87, 112, 78, 36),
        Array(35, 44, 27, 41, 65, 38),
        Array(41, 46, 13, 74, 71, 27)
      ))
  }
}
