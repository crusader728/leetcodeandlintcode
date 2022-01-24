package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree366 {
    public List<List<Integer>> findLeaves(TreeNode root) {
        if(root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private int dfs(TreeNode root, List<List<Integer>> result) {
        if(root.left == null && root.right == null) {
            if(result.isEmpty()) {
                result.add(new ArrayList<>());
            }
            result.get(0).add(root.val);
            return 0;
        } else {
            int left = -1;
            int right = -1;
            if(root.left != null) {
                left = dfs(root.left, result);
            }
            if(root.right != null) {
                right = dfs(root.right, result);
            }
            int height = Math.max(left, right) + 1;
            if(height >= result.size()) {
                result.add(new ArrayList<>());
            }
            result.get(height).add(root.val);
            return height;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
