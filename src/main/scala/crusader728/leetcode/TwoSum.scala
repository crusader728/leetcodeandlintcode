package scala.crusader728.leetcode


object TwoSum {
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    Solver(nums, target).solution.head
  }
  private case class Solver(nums: Array[Int], target: Int) {
    type State = Map[Int, List[Int]]
    private def step(i: Int, state: State): (State, List[(Int, Int)]) = {
      val other = target - nums(i)
      val output = state.getOrElse(other, List.empty).map(x => (x, i))
      val nextState = state + (nums(i) -> (i :: state.getOrElse(nums(i), List.empty)))
      (nextState, output)
    }

    private def stepF(i: Int): State => (State, List[(Int, Int)]) = s => step(i, s)

    type StateM[A] = State => (State, A)

    private def sequence[A](input: LazyList[StateM[A]]): StateM[LazyList[A]] = {
      s => input.foldLeft((s, LazyList.empty[A])) { case ((state, v), sm) =>
        val (nextState, output) = sm.apply(state)
        (nextState, output #:: v)
      }
    }

    val solution: LazyList[Array[Int]] = {
      val output = sequence(LazyList.from(nums.indices).map(stepF)).apply(Map.empty)._2
      output.flatten.map { case (i, j) => Array(i, j) }
    }
  }


}
