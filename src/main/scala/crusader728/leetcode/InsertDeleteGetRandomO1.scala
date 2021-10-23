package scala.crusader728.leetcode

object InsertDeleteGetRandomO1 {
  class RandomizedSet() {
    type Value = Int
    type Index = Int

    /** Initialize your data structure here. */
    case class State(m: Map[Value, Index], rev: Map[Index, Value], size: Int)

    var s = State(Map(), Map(), 0)
    val rng = new scala.util.Random()

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    def insert(`val`: Int): Boolean = {
      s.m.get(`val`) match {
        case None => {
          s =
            State(s.m + (`val` -> s.size), s.rev + (s.size -> `val`), s.size + 1)
          true
        }
        case _ => false
      }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    def remove(`val`: Int): Boolean = {
      s.m.get(`val`) match {
        case None => false
        case Some(i) => {
          val m1 = s.m - `val`
          val rev1 = s.rev - i
          val newSize = s.size - 1
          if (newSize == 0) {
            s = State(m1, rev1, newSize)
            true
          } else {
            rev1.get(newSize) match {
              case None => {
                s = State(m1, rev1, newSize)
                true
              }
              case Some(u) => {
                val rev2 = rev1 - newSize
                val m2 = m1 + (u -> i)
                val rev3 = rev2 + (i -> u)
                s = State(m2, rev3, newSize)
                true
              }
            }
          }
        }
      }
    }

    /** Get a random element from the set. */
    def getRandom(): Int = {
      val i = rng.nextInt(s.size)
      s.rev(i)
    }

  }
}
