package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest1110 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> vals = new HashSet<>();
        for(int v: to_delete) {
            vals.add(v);
        }
        List<TreeNode> result = new ArrayList<>();
        dfs(root, true, vals, result);
        return result;
    }


    private TreeNode dfs(TreeNode root, boolean isRoot, Set<Integer> vals, List<TreeNode> acc) {
        if(root == null) {
            return null;
        }

        if(!vals.contains(root.val) && isRoot) {
            acc.add(root);
        }

        root.left = dfs(root.left, vals.contains(root.val), vals, acc);
        root.right = dfs(root.right, vals.contains(root.val), vals, acc);
        return vals.contains(root.val) ? null : root;
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
