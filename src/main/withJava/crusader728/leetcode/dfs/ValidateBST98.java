package withJava.crusader728.leetcode.dfs;

public class ValidateBST98 {
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(root.left == null && root.right == null) {
            return true;
        }
        else {
            boolean childrenIsValid = isValidBST(root.left) && isValidBST(root.right);
            if(!childrenIsValid) {
                return false;
            } else {
                TreeNode p;
                if(root.left != null) {
                    p = root.left;
                    while(p.right != null) {
                        p = p.right;
                    }
                    if(p.val >= root.val) {
                        return false;
                    }
                }
                if(root.right != null) {
                    p = root.right;
                    while(p.left != null) {
                        p = p.left;
                    }
                    if(p.val <= root.val) {
                        return false;
                    }
                }
                
                return true;
            }
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
