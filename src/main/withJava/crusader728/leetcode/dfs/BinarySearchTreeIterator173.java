package withJava.crusader728.leetcode.dfs;

import java.util.Stack;

public class BinarySearchTreeIterator173 {
    private static class BSTIterator {
        private Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            TreeNode p = root;
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
        }
        
        public int next() {
            if(!hasNext()) {
                throw new IllegalStateException();
            }

            TreeNode pop = stack.pop();
            TreeNode p = pop.right;
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            return pop.val;
        }
        
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
