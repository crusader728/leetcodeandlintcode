package scala.crusader728.leetcode

object RobotBoundedInCircle {
  case class Cord(x: Int, y: Int)
  sealed trait Direction {
    def turnLeft: Direction
    def turnRight: Direction
    def step(cord: Cord): Cord
  }
  case object N extends Direction {
    override def turnLeft: Direction = W

    override def turnRight: Direction = E

    override def step(cord: Cord): Cord = cord.copy(y = cord.y + 1)
  }
  case object S extends Direction {
    override def turnLeft: Direction = E

    override def turnRight: Direction = W

    override def step(cord: Cord): Cord = cord.copy(y = cord.y - 1)
  }
  case object E extends Direction {
    override def turnLeft: Direction = N

    override def turnRight: Direction = S

    override def step(cord: Cord): Cord = cord.copy(x = cord.x + 1)
  }
  case object W extends Direction {
    override def turnLeft: Direction = S

    override def turnRight: Direction = N

    override def step(cord: Cord): Cord = cord.copy(x = cord.x - 1)
  }

  case class State(cord: Cord, direction: Direction)

  def step(ch: Char, s: State): State = ch match {
    case 'G' => s.copy(cord = s.direction.step(s.cord))
    case 'L' => s.copy(direction = s.direction.turnLeft)
    case 'R' => s.copy(direction = s.direction.turnRight)
    case _ => throw new RuntimeException
  }


  def isRobotBounded(instructions: String): Boolean = {
    val start = State(Cord(0, 0), N)
    val end = instructions.foldLeft(start) {case (s, ch) => step(ch, s) }
    start.cord == end.cord || end.direction != N
  }
}
