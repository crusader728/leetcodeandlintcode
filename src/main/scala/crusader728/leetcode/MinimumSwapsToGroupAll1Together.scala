package scala.crusader728.leetcode


object MinimumSwapsToGroupAll1Together {

  import scala.reflect.ClassTag
  import scala.collection.View

  type Window = collection.mutable.Queue[Int]
  type Count = Int
  type State = (Window, Count)

  val getWindow: State => Window = _._1
  val getCount: State => Count = _._2

  val step: Count => Int => State => (Int, State) = k => i => s => {
    val w = getWindow(s)
    val c = getCount(s)
    w.enqueue(i)
    val countAfterAdd = if (i == 1) {
      c + 1
    } else {
      c
    }
    val countAfterDequeue = if (w.size > k) {
      val valueRemoved = w.dequeue()
      if (valueRemoved == 1) countAfterAdd - 1 else countAfterAdd
    } else {
      countAfterAdd
    }
    val output = if (w.size == k) k - countAfterDequeue else Int.MaxValue
    (output, (w, countAfterDequeue))
  }

  type Embellished[A] = State => (A, State)

  def sequence[A](array: View[Embellished[A]])(implicit t: ClassTag[A]): Embellished[Array[A]] = s => {
    val buffer = collection.mutable.ArrayBuffer.empty[A]
    val finalState = array.foldLeft(s) { (acc, embellished) => {
      val (output, state) = embellished(acc)
      println(state)
      buffer.append(output)
      state
    }
    }
    (buffer.toArray[A], finalState)
  }


  def minSwaps(data: Array[Int]): Int = {
    val totalOnes = data.count(i => i == 1)
    val initialState: State = (collection.mutable.Queue.empty, 0)
    val (output, _) = sequence[Int](data.view.map(i => step(totalOnes)(i))).apply(initialState)
    output.min
  }
}
