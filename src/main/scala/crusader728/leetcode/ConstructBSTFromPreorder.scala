package scala.crusader728.leetcode

import scala.annotation.tailrec
import scala.util.control.TailCalls._

object ConstructBSTFromPreorder {

  def bstFromPreorder(preorder: Array[Int]): TreeNode = {
    @tailrec
    def search(l: Int, r: Int, target: Int): Int = {
      if(l >= r) {
        l
      } else {
        val mid = l + (r - l) / 2
        val v = preorder(mid)
        if(v > target) {
          search(l, mid, target)
        } else if(v < target) {
          search(mid + 1, r, target)
        }else {
          throw new RuntimeException
        }
      }

    }

    def helper(l: Int, r: Int): TailRec[TreeNode] = if(l >= r) {
      done(null)
    } else if(l == r - 1) {
      val leaf = new TreeNode()
      leaf.right = null
      leaf.left = null
      leaf.value = preorder(l)
      done(leaf)
    } else {
      val idx: Int = search(l + 1, r, preorder(l))
      for {
        left <- tailcall(helper(l + 1, idx))
        right <- tailcall(helper(idx, r))
      } yield new TreeNode(preorder(l), left, right)
    }

    helper(0, preorder.length).result
  }
}
