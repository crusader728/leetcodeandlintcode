package scala.crusader728.leetcode

object AllNodesDistanceKBinaryTree {

  def buildParent(root: TreeNode, m: Map[TreeNode, TreeNode]): Map[TreeNode, TreeNode] = {
    if(root == null) {
      m
    } else {
      val m1 = buildParent(root.right, buildParent(root.left, m))
      if(root.left != null) {
        (if(root.right != null) {
          m1 + (root.right -> root)
        } else {
          m1
        }) + (root.left -> root)
      } else {
        if(root.right != null) {
          m1 + (root.right -> root)
        } else {
          m1
        }
      }
    }
  }

  def distanceK(root: TreeNode, target: TreeNode, k: Int): List[Int] = {
    val parents: Map[TreeNode, TreeNode] = buildParent(root, Map.empty)

    @scala.annotation.tailrec
    def bfs(current: Set[TreeNode], visited: Set[TreeNode], dist: Int): Set[TreeNode] = {
      if(dist == 0) {
        current
      } else if(current.isEmpty) {
        Set.empty
      } else {
        val newVisited = visited.union(current)
        val next = current.flatMap(node => {
          val s1 = if (node.left == null) Set.empty else Set(node.left)
          val s2 = if (node.right == null) Set.empty else Set(node.right)
          val s3 = if (parents.contains(node)) Set(parents(node)) else Set.empty
          s1 ++ s2 ++ s3
        }).diff(newVisited)
        bfs(next, newVisited, dist - 1)
      }
    }

    bfs(Set(target), Set.empty, k).toList.map(_.value)
  }

  def main(args: Array[String]): Unit = {
    val tree = new TreeNode(0,
      _left = null,
      _right = new TreeNode(1,
        _left = null,
        _right = new TreeNode(2,
          _left = null,
          _right = new TreeNode(3,
            _left = null,
            _right = new TreeNode(4)
          )
        )
      ))

    buildParent(tree, Map.empty)
  }

}
