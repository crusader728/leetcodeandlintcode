package scala.crusader728.leetcode

object FlattenNestedListIterator {
  class NestedIterator(_nestedList: List[NestedInteger]) {
    def helper(l: Seq[NestedInteger]): LazyList[Int] = {
      l.foldRight(LazyList[Int]()) {case (ni, acc) => {
        if(ni.isInteger) {
          ni.getInteger #:: acc
        } else {
          helper(ni.getList) #::: acc
        }
      }}
    }

    var stream: LazyList[Int] = helper(_nestedList)
    def next(): Int = {
      val r = stream.head
      stream = stream.tail
      r
    }

    def hasNext(): Boolean = {
      stream.nonEmpty
    }
  }
}
