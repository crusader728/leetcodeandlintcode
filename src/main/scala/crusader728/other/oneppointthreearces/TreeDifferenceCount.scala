package scala.crusader728.other.oneppointthreearces

object TreeDifferenceCount {
  case class Tree(key: String, value: String, children: List[Tree])


  def countNodes(from: Tree): Int = from.children.map(countNodes).sum + 1

  def countDifference(from: Tree, to: Tree): Int = (from, to) match {
    case (null, null) => 0
    case (_, null) => countNodes(from)
    case (null, _) => countNodes(to)
    case (_, _) => if(from.key == to.key) {
      val rootValueChanged = if(from.value == to.value) {
        0
      } else {
        1
      }
      val keyToTreesInFrom = from.children.groupBy(_.key)
      val keyToTreesInTo = to.children.groupBy(_.key)
      if(keyToTreesInFrom.values.exists(_.size > 1) || keyToTreesInTo.values.exists(_.size > 1)) {
        throw new IllegalArgumentException
      } else {
        val keysInFrom = keyToTreesInFrom.keySet
        val keysInTo = keyToTreesInTo.keySet
        val diff1 = keysInFrom.diff(keysInTo).foldLeft(0) {case (sum, k) => keyToTreesInFrom(k).map(t => countDifference(t, null)).sum + sum }
        val diff2 = keysInTo.diff(keysInFrom).foldLeft(0) {case (sum, k) => keyToTreesInTo(k).map(t => countDifference(null, t)).sum + sum }
        val diff3 = keysInTo.intersect(keysInFrom).foldLeft(0) {case (sum, k) => {
          val t1 = keyToTreesInFrom(k).head
          val t2 = keyToTreesInTo(k).head
          countDifference(t1, t2) + sum
        }}
        diff1 + diff2 + diff3 + rootValueChanged
      }
    } else {
      countNodes(from) + countNodes(to)
    }
  }
}
