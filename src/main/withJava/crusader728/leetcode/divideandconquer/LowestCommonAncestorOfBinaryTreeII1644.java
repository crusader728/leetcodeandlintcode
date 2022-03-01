package withJava.crusader728.leetcode.divideandconquer;

public class LowestCommonAncestorOfBinaryTreeII1644 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root == null) {
            return null;
        } 
        if(exists(root, p) && exists(root, q)) {
            return lca(root, p, q);
        } else {
            return null;
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

    private boolean exists(TreeNode r, TreeNode p) {
        if(r == null) {
            return false;
        } else if(r == p) {
            return true;
        } else {
            return exists(r.left, p) || exists(r.right, p);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
