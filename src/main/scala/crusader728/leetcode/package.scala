package scala.crusader728

package object leetcode {

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }

  trait NestedInteger {

    // Return true if this NestedInteger holds a single integer, rather than a nested list.
    def isInteger: Boolean

    // Return the single integer that this NestedInteger holds, if it holds a single integer.
    def getInteger: Int

    // Set this NestedInteger to hold a single integer.
    def setInteger(i: Int): Unit

    // Return the nested list that this NestedInteger holds, if it holds a nested list.
    def getList: Array[NestedInteger]

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    def add(ni: NestedInteger): Unit
  }

  class Node(var _value: Int) {
    var value: Int = _value
    var prev: Node = null
    var next: Node = null
    var child: Node = null
  }

  class NaryTreeNode(var _value: Int) {
    var value: Int = _value
    var children: List[NaryTreeNode] = List()
  }
}
