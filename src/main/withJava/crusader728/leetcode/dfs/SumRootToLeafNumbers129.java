package withJava.crusader728.leetcode.dfs;

public class SumRootToLeafNumbers129 {
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return -1;
        }
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int current) {
        if(root == null) {
            return 0;
        } 
        if(root.left == null && root.right == null) {
            return current * 10 + root.val;
        }
        return dfs(root.left, current * 10 + root.val) + dfs(root.right, current * 10 + root.val);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
