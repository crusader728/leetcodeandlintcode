package withJava.crusader728.leetcode.dfs;

public class CheckCompletenessOfBinaryTree958 {
    public boolean isCompleteTree(TreeNode root) {
        return helper(root) >= 0;
    }

    private int helper(TreeNode node) {
        if(node == null) {
            return 0;
        } else if(node.left == null && node.right == null) {
            return 1;
        } else {
            int l = helper(node.left);
            int r = helper(node.right);
            if((l & (l + 1)) == 0 && l / 2 <= r && r < l) {
                return l + r + 1;
            }
            if((r & (r + 1)) == 0 && r <= l && l <= r * 2 + 1) {
                return l + r + 1;
            }
            return -1;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
