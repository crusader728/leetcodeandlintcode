package scala.crusader728.leetcode.monotonic

object BuildingWithOceanView1762 {
  def findBuildings(heights: Array[Int]): Array[Int] = {
    heights.indices.foldLeft(List.empty[Int]) {case (stack, i) => stack match {
      case Nil => List(i)
      case x :: xs if heights(x) > heights(i) => i :: stack
      case x :: xs if heights(x) <= heights(i) => i :: stack.dropWhile(j => heights(j) <= heights(i))
    } }
      .reverse.toArray
  }

  def main(args: Array[String]): Unit = {
    println(findBuildings(Array(4,3,2,1)))
  }
}
