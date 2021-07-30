package scala.crusader728.leetcode

object TrappingRainWater {
  def trap(height: Array[Int]): Int = {
    @scala.annotation.tailrec
    def loop(i: Int, stack: List[Int], acc: Int): Int = {
      i match {
        case n if n == height.size => acc
        case _ => stack match {
          case Nil => loop(i + 1, i :: stack, acc)
          case x :: xs => if(height(i) <= height(x)) {
            loop(i + 1, i :: stack, acc)
          } else {
            val (newStack, newAcc) = calc(i, stack, acc)
            loop(i + 1, newStack, newAcc)
          }
        }
      }
    }

    @scala.annotation.tailrec
    def calc(current: Int, stack: List[Int], acc: Int): (List[Int], Int) = {
      stack match {
        case Nil => (Nil, acc)
        case x :: Nil => if(height(x) >= height(current)) {
          (current :: stack, acc)
        } else {
          (current :: Nil, acc)
        }
        case x :: xs => if(height(x) >= height(current)) {
          (current :: stack, acc)
        } else {
          val dist = current - xs.head - 1
          val boundedHeight = Math.min(height(current), height(xs.head)) - height(x);
          calc(current, xs, acc + dist * boundedHeight)
        }
      }
    }

    loop(0, Nil, 0)
  }
}
