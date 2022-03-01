package withJava.crusader728.leetcode.divideandconquer;

public class LowestCommonAncestorOfBinaryTreeIV1676 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        return helper(root, nodes, 0, nodes.length);
    }

    private TreeNode helper(TreeNode root, TreeNode[] nodes, int left, int right) {
        if(left >= right) {
            return null;
        } else if(left == right - 1) {
            return nodes[left];
        } else if(left == right - 2) {
            return lca(root, nodes[left], nodes[left + 1]);
        } else {
            int mid = left + (right - left) / 2;
            return lca(root, helper(root, nodes, left, mid), helper(root, nodes, mid, right));
        }
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftAns = lca(root.left, p, q);
        if(leftAns == null) {
            return lca(root.right, p, q);
        } else {
            TreeNode rightAns = lca(root.right, p, q);
            if(rightAns == null) {
                return leftAns;
            } else {
                return root;
            }
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
