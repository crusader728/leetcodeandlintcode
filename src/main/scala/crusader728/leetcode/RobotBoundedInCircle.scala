package scala.crusader728.leetcode

object RobotBoundedInCircle {
  case class Cord(x: Int, y: Int)

  trait DirAlg[T] {
    def forNorth: T
    def forSouth: T
    def forEast: T
    def forWest: T
  }

  final class TurnLeft extends DirAlg[]


  def isRobotBounded(instructions: String): Boolean = {
    val start = State(Cord(0, 0), N)
    val end = instructions.foldLeft(start) {case (s, ch) => step(ch, s) }
    start.cord == end.cord || end.direction != N
  }
}
