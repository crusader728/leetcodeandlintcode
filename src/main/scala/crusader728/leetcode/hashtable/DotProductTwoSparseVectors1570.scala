package scala.crusader728.leetcode.hashtable

object DotProductTwoSparseVectors1570 {
  class SparseVector(nums: Array[Int]) {
    val pairs = nums.indices.foldLeft(Map.empty[Int, Int]) { case (acc, i) => if(nums(i) == 0) acc else acc + (i -> nums(i)) }
    // Return the dotProduct of two sparse vectors
    def dotProduct(vec: SparseVector): Int = {
      val idx1 = this.pairs.keySet
      val idx2 = vec.pairs.keySet

      val join = idx1.intersect(idx2)
      join.foldLeft(0) {case (acc, i) => acc + pairs(i) * vec.pairs(i) }
    }
  }
}
