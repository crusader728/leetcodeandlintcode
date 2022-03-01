package withJava.crusader728.leetcode.divideandconquer;

public class LowestCommonAncestorOfBinaryTree236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        } else if(root == p || root == q) {
            return root;
        } else {
            TreeNode ansLeft = lowestCommonAncestor(root.left, p, q);
            if(ansLeft == null) {
                return lowestCommonAncestor(root.right, p, q);
            } else {
                TreeNode ansRight = lowestCommonAncestor(root.right, p, q);
                if(ansRight == null) {
                    return ansLeft;
                } else {
                    return root;
                }
            }
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
