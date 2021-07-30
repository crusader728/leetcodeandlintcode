package java.crusader728.lintcode;

import java.util.HashMap;
import java.util.Map;

public class ID614_LongestConsecutive2 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int longestConsecutive2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Map<TreeNode, Integer> up = new HashMap<>();
        Map<TreeNode, Integer> down = new HashMap<>();
        return helper(root, up, down);
    }

    private int helper(TreeNode node,
                       Map<TreeNode, Integer> up,
                       Map<TreeNode, Integer> down) {
        if(node == null) {
            return 0;
        }
        int lresult = helper(node.left, up, down);
        int rresult = helper(node.right, up, down);
        int max = Math.max(lresult, rresult);
        if(node.left == null && node.right == null) {
            up.put(node, 1);
            down.put(node, 1);
            return 1;
        } else if(node.left != null && node.right == null) {
            if(node.left.val == node.val + 1) {
                up.put(node, up.get(node.left) + 1);
                down.put(node, 1);
                return Math.max(up.get(node), max);
            } else if(node.left.val == node.val - 1) {
                down.put(node, down.get(node.left) + 1);
                up.put(node, 1);
                return Math.max(down.get(node),max);
            } else {
                up.put(node, 1);
                down.put(node, 1);
                return Math.max(1, max);
            }
        } else if(node.left == null) {
            if(node.right.val == node.val + 1) {
                up.put(node, up.get(node.right) + 1);
                down.put(node, 1);
                return Math.max(up.get(node), max);
            } else if(node.right.val == node.val - 1) {
                down.put(node, down.get(node.right) + 1);
                up.put(node, 1);
                return Math.max(down.get(node), max);
            } else {
                up.put(node, 1);
                down.put(node, 1);
                return Math.max(1, max);
            }
        } else {
            if(node.left.val == node.val + 1 && node.right.val == node.val - 1) {
                up.put(node, Math.max(1, up.get(node.left) + 1));
                down.put(node, Math.max(1, down.get(node.right) + 1));
                return Math.max(up.get(node) + down.get(node) - 1, max);
            } else if(node.left.val == node.val + 1 && node.right.val == node.val + 1) {
                up.put(node, Math.max(up.get(node.left), up.get(node.right)) + 1);
                down.put(node, 1);
                return Math.max(up.get(node), max);
            } else if(node.left.val == node.val - 1 && node.right.val == node.val - 1) {
                down.put(node, Math.max(down.get(node.left), down.get(node.right)) + 1);
                up.put(node, 1);
                return Math.max(down.get(node), max);
            } else if(node.left.val == node.val - 1 && node.right.val == node.val + 1) {
                up.put(node, Math.max(1, up.get(node.right) + 1));
                down.put(node, Math.max(1, down.get(node.left) + 1));
                return Math.max(up.get(node) + down.get(node) - 1, max);
            } else if(node.left.val == node.val + 1) {
                up.put(node, up.get(node.left) + 1);
                down.put(node, 1);
                return Math.max(up.get(node), max);
            } else if(node.left.val == node.val - 1) {
                down.put(node, down.get(node.left) + 1);
                up.put(node, 1);
                return Math.max(down.get(node), max);
            } else if(node.right.val == node.val + 1) {
                up.put(node, up.get(node.right) + 1);
                down.put(node, 1);
                return Math.max(up.get(node), max);
            } else if(node.right.val == node.val - 1) {
                down.put(node, down.get(node.right) + 1);
                up.put(node, 1);
                return Math.max(down.get(node), max);
            } else {
                up.put(node, 1);
                down.put(node, 1);
                return Math.max(1, max);
            }
        }
    }
}
