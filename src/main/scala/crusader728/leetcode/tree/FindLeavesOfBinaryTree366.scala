package scala.crusader728.leetcode.tree

import scala.crusader728.leetcode.TreeNode

object FindLeavesOfBinaryTree366 {
  def findLeaves(root: TreeNode): List[List[Int]] = {
    val (h, depthMap) = collectHeight(root)
    (0 to h).foldRight(List.empty[List[Int]]) {case (i, acc) => {
      if(depthMap.contains(i)) {
        depthMap(i).map(_.value) :: acc
      } else {
        acc
      }
    }}
  }


  def collectHeight(root: TreeNode): (Int, Map[Int, List[TreeNode]]) = root match {
    case null => (-1, Map.empty)
    case n if n.left == null && n.right == null => (0, Map(0 -> List(n)))
    case n => {
      val (h1, m1): (Int, Map[Int, List[TreeNode]]) = if(n.left == null) {
        (-1, Map.empty)
      } else {
        collectHeight(n.left)
      }

      val (h2, m2): (Int, Map[Int, List[TreeNode]]) = if(n.right == null) {
        (-1, Map.empty)
      } else {
        collectHeight(n.right)
      }

      val h = Math.max(h1, h2) + 1
      val depthMap = m1.foldLeft(m2) {case (acc, (k, l)) => {
        acc + (k -> (acc.getOrElse(k, List.empty) ++ l))
      }}
      (h, depthMap + (h -> List(root)))
    }
  }

  def main(args: Array[String]): Unit = {
    println(findLeaves(new TreeNode(1,
      new TreeNode(2,
        new TreeNode(4, null, null),
        new TreeNode(5, null, null)
      ),
      new TreeNode(3, null, null)
    )))
  }
}
