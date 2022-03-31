package withJava.crusader728.leetcode.bfs;

import java.util.*;

public class SameTree100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return true;
        }
        if(p == null || q == null) {
            return false;
        }

        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(p);
        q2.offer(q);
        while(!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode top1 = q1.poll();
            TreeNode top2 = q2.poll();
            if(top1.val != top2.val) {
                return false;
            }
            if(!check(top1.left, top2.left)) {
                return false;
            }
            if(!check(top1.right, top2.right)) {
                return false;
            }
            if(top1.left != null) {
                q1.offer(top1.left);
                q2.offer(top2.left);
            }
            if(top1.right != null) {
                q1.offer(top1.right);
                q2.offer(top2.right);
            }
        }
        return true;
    }

    private boolean check(TreeNode n1, TreeNode n2) {
        if(n1 == null && n2 == null) {
            return true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        if(n1.val != n2.val) {
            return false;
        }
        return true;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
