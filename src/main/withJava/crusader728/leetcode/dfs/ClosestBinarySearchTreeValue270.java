package withJava.crusader728.leetcode.dfs;

public class ClosestBinarySearchTreeValue270 {
    public int closestValue(TreeNode root, double target) {
        int val;
        int closest = root.val;
        TreeNode p = root;
        while(p != null) {
            val = p.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            p = p.val > target ? p.left : p.right;
        }
        return closest;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
