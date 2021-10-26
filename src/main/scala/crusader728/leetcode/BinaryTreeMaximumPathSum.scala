package scala.crusader728.leetcode

object BinaryTreeMaximumPathSum {
  type MaxPathSum = Int
  type LocalMax = Int

  //maxPathSum of a tree is one of these three conditions:
  //1. a path come from left subtree - root - right subtree
  //2. a path only from left subtree
  //3. a path only from right subtree
  //4. a path root -> left subtree
  //5. a path root -> right subtree

  // dfs with memorization

  trait Memo[K, V] {
    def memo(f: K => V): K => V
  }

  object Memo {
    def apply[K, V](y: (K => V) => K => V): Memo[K, V] = (f: K => V) => y(f)
    def mutableMapMemo[K, V](m: collection.mutable.Map[K, V]): Memo[K, V] = Memo(f => k => m.getOrElseUpdate(k, f(k)))
  }

  case class Solver(root: TreeNode) {
    val dp: TreeNode => (LocalMax, MaxPathSum) = Memo.mutableMapMemo(collection.mutable.HashMap.empty[TreeNode, (LocalMax, MaxPathSum)]).memo {
      case x if x == null => (0, 0)
      case x if x.left == null && x.right == null => (x.value, x.value)
      case x if x.left == null => {
        val (rlm, rms) = dp(x.right)
        val lm = Math.max(0, rlm) + x.value
        val ms = Math.max(rms, lm)
        (lm, ms)
      }
      case x if x.right == null => {
        val (llm, lms) = dp(x.left)
        val lm = Math.max(0, llm) + x.value
        val ms = Math.max(lms, lm)
        (lm, ms)
      }
      case x@_ => {
        val (rlm, rms) = dp(x.right)
        val (llm, lms) = dp(x.left)
        val lm = Math.max(Math.max(rlm, llm), 0) + x.value
        val ms = List(
          rms,
          lms,
          x.value + Math.max(0, rlm),
          x.value + Math.max(0, llm),
          x.value + rlm + llm
        ).max
        (lm, ms)
      }
    }
  }

  def maxPathSum(root: TreeNode): Int = {
    Solver(root).dp(root)._2
  }
}
