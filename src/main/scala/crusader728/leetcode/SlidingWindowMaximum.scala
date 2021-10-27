package scala.crusader728.leetcode

object SlidingWindowMaximum {
  type Value = Int
  type Q = collection.mutable.ArrayDeque[Int]
  type Idx = Int
  type Window = (Idx, Idx)

  val add: Value => Q => Q = v => q => {
    q.filterInPlace(x => x >= v).append(v)
  }

  val remove: Value => Q => Q = v => q => {
    if(q.head == v) {
      q.tail
    } else {
      q
    }
  }

  val top: Q => Value = q => q.head

  val leftOfWindow: Window => Idx = w => w._1
  val rightOfWindow: Window => Idx = w => w._2

  type State = (Q, Window)

  val getWindow: State => Window = s => s._2
  val getQ: State => Q = s => s._1

  def step(nums: Array[Int], k: Int)(i: Idx)(state: State): (Value, State) = {
    val left = leftOfWindow(getWindow(state))
    val right = rightOfWindow(getWindow(state))


    val newRight = right + 1
    val value = nums(i)
    val q = getQ(state)
    println(i, left, right, q)
    if(newRight - left + 1> k) {
      val newLeft = left + 1
      val newWindow = (newLeft, newRight)
      val qAfterRemove = remove(nums(left))(q)
      val qAfterAdd = add(nums(newRight))(qAfterRemove)
      val newState = (qAfterAdd, newWindow)
      (top(qAfterAdd), newState)
    } else {
      val newQ = add(value)(q)
      val newState = (newQ, (left, newRight))
      (top(newQ), newState)
    }
  }

  def stepM(nums: Array[Int], k: Int)(i: Idx): State => (Value, State) = s => step(nums, k)(i)(s)

  type StateM[A] = State => (A, State)
  def sequence[A](l: IndexedSeq[StateM[A]]): StateM[IndexedSeq[A]] = s => {
    l.foldLeft((s, IndexedSeq.empty[A])) {case (acc , sm) => {
      val accState: State = acc._1
      val accList = acc._2
      val (a, newS) = sm(accState)
      (newS, accList.appended(a))
    } }
      .swap
  }

  def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] = {
    val initialQ: Q = collection.mutable.ArrayDeque.empty
    val initialWindow: Window = (0, -1)
    val initialState: State = (initialQ, initialWindow)
    val (output, _) = sequence(nums.indices.map(i => stepM(nums, k)(i)))(initialState)
    output.drop(k - 1).toArray
  }
}
