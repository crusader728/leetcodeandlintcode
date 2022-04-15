package withJava.crusader728.leetcode.divideandconquer;

public class TrimBinarySearchTree669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) {
            return null;
        }

        if(root.val > high) {
            return trimBST(root.left, low, high);
        } else if(root.val == high) {
            root.left = trimBST(root.left, low, high);
            root.right = null;
            return root;
        } else if(root.val == low) {
            root.left = null;
            root.right = trimBST(root.right, low, high);
            return root;
        } else if(root.val < low) {
            return trimBST(root.right, low, high);
        } else {
            root.left = trimBST(root.left, low, root.val);
            root.right = trimBST(root.right, root.val, high);
            return root;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
