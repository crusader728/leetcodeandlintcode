package withJava.crusader728.leetcode.dfs;


public class DiameterOfBinaryTree543 {
    public int diameterOfBinaryTree(TreeNode root) {
        Info r = helper(root);
        if(r == null) {
            return 0;
        } else {
            return r.diameter;
        }
    }


    private Info helper(TreeNode root) {
        if(root == null) {
            return null;
        } 
        if(root.left == null && root.right == null) {
            Info r = new Info();
            r.diameter = 0;
            r.h = 0;
            return r;
        }
        Info l = helper(root.left);
        Info r = helper(root.right);
        int lh = 0, rh = 0, ld = 0, rd = 0;
        if(l != null) {
            lh = l.h + 1;
            ld = l.diameter;
        }
        if(r != null) {
            rh = r.h + 1;
            rd = r.diameter;
        }
        Info result = new Info();
        result.h = Math.max(lh, rh);
        result.diameter = Math.max(ld, Math.max(rd, lh + rh));
        
        return result;

    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    private static class Info {
        int diameter;
        int h;
    } 
}
