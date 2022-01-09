package scala.crusader728.leetcode.dfs

import scala.crusader728.leetcode.NestedInteger

object NestedListWeightSumII364 {
  def toMap(baseHeight: Int, ni: NestedInteger): Map[Int, List[Int]] = {
    if(ni.isInteger) {
      Map(baseHeight -> List(ni.getInteger))
    } else {
      ni.getList.map(elem => toMap(baseHeight + 1, elem))
        .foldLeft(Map.empty[Int, List[Int]]) {case (acc, sub) => {
          sub.foldLeft(acc) { case (m, (k, v)) => {
            m + (k -> (v ++ m.getOrElse(k, List.empty)))
          }}
        }}
    }
  }

  def depthSumInverse(nestedList: List[NestedInteger]): Int = {
    val weightMap = nestedList.map(ni => toMap(1, ni))
      .foldLeft(Map.empty[Int, List[Int]]) {case (acc, sub) => {
        sub.foldLeft(acc) {case (m, (k, v)) => {
          m + (k -> (v ++ m.getOrElse(k, List.empty)))
        }}
      }}
      .view
      .mapValues(l => l.sum)
    weightMap.keySet.maxOption match {
      case None => 0
      case Some(maxWeight) => {
        (1 to maxWeight).foldLeft(0) {case (acc, w) => {
          if(weightMap.contains(w)) {
            acc + (maxWeight - w + 1) * weightMap(w)
          } else {
            acc
          }
        }}
      }
    }

  }

}
