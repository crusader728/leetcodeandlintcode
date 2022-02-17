package withJava.crusader728.leetcode.dfs;

public class MaximumDepthOfBinaryTree104 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        } else if(root.left != null && root.right != null) {
            return 1;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}
