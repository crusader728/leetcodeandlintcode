package withJava.crusader728.leetcode.dfs;

public class RangeSumInBST938 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) {
            return 0;
        } else if(root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else if(root.val > high) {
            return rangeSumBST(root.left, low, high);
        } else {
            return rangeSumBST(root.left, low, root.val) + rangeSumBST(root.right, root.val, high) + root.val;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
