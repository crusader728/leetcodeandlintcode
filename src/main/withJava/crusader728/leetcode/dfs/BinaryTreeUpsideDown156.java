package withJava.crusader728.leetcode.dfs;

public class BinaryTreeUpsideDown156 {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        Pair p = helper(root);
        if(p == null) {
            return null;
        } else {
            return p.rootAfterFlip;
        }
    }

    private Pair helper(TreeNode root) {
        if(root == null) {
            return null;
        } 
        if(root.left == null && root.right == null) {
            Pair pair = new Pair();
            pair.rootBeforeFlip = root;
            pair.rootAfterFlip = root;
            return pair;
        }
        else if(root.left != null) {
            Pair sub = helper(root.left);
            sub.rootBeforeFlip.left = root.right;
            sub.rootBeforeFlip.right = root;
            root.left = null;
            root.right = null;
            Pair pair = new Pair();
            pair.rootBeforeFlip = root;
            pair.rootAfterFlip = sub.rootAfterFlip;
            return pair;
        } else {
            throw new IllegalArgumentException("right node must have sibling");
        }

    }

    private static class Pair {
        TreeNode rootAfterFlip;
        TreeNode rootBeforeFlip;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
