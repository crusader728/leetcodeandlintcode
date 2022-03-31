package withJava.crusader728.leetcode.dfs;

import java.util.*;

public class AllNodesDistanceK863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        dfs(target, k, root, ans);
        return ans;
    }

    private int dfs(TreeNode target, int k, TreeNode root, List<Integer> ans) {
        if(root == null) {
            return -1;
        }
        if(root == target) {
            subtree(target, k, ans);
            return 0;
        }
        int l = dfs(target, k, root.left, ans);
        int r = dfs(target, k, root.right, ans);
        if(l == -1 && r == -1) {
            return -1;
        } else if(l == -1) {
            int d = r + 1;
            if(d == k) {
                ans.add(root.val);
            } else if(d < k) {
                subtree(root.left, k - d - 1, ans);
            }
            return d;
        } else {
            int d = l + 1;
            if(d == k) {
                ans.add(root.val);
            } else if(d < k) {
                subtree(root.right, k - d - 1, ans);
            }
            return d;
        }
    }

    private void subtree(TreeNode target, int k, List<Integer> ans) {
        if(target == null) {
            return;
        }
        if(k == 0) {
            ans.add(target.val);
            return;
        }
        subtree(target.left, k - 1, ans);
        subtree(target.right, k - 1, ans);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
