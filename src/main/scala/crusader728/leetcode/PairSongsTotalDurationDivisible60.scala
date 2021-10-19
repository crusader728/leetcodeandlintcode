package scala.crusader728.leetcode



object PairSongsTotalDurationDivisible60 {
  import scala.collection.mutable.ArrayBuffer
  import scala.reflect.ClassTag
  type State = Map[Int, List[Int]]

  def step(x: Int, s: State): (State, Int) = {
    val module1 = x % 60
    val target = (60 - module1) % 60
    val output = if(s.contains(target)) {
      s(target).size
    } else {
      0
    }
    val newS = s + (module1 -> (x :: s.getOrElse(module1, List.empty)))
    (newS, output)
  }

  case class MyStateM[A](runs: State => (State, A)) { self =>
    def map[B](f: A => B): MyStateM[B] = MyStateM[B](s => {
      val (s1, o) = self.runs(s)
      (s1, f(o))
    })

    def flatMap[B](f: A => MyStateM[B]): MyStateM[B] = MyStateM[B](s => {
      val (s1, o) = self.runs(s)
      f(o).runs(s1)
    })
  }

  def sequence[A : ClassTag](input: Array[MyStateM[A]]): MyStateM[Array[A]] = MyStateM[Array[A]](s => {
    val (finalS, buffer) = input.foldLeft((s, ArrayBuffer.empty[A])) { case ((acc, buffer), m) => {
      val (nextS, o) = m.runs(acc)
      (nextS, buffer.append(o))
    }}
    (finalS, buffer.toArray)
  })



  def numPairsDivisibleBy60(time: Array[Int]): Int = {
    sequence[Int](time.map(t => MyStateM((step _).curried.apply(t)))).runs(Map.empty)._2.sum
  }
}
