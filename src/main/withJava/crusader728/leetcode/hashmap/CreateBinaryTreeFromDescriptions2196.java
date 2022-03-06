package withJava.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class CreateBinaryTreeFromDescriptions2196 {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> valueNodeMap = new HashMap<>();
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        for(int[] desc: descriptions) {
            int parent = desc[0];
            int child = desc[1];
            boolean isLeft = desc[2] == 1;
            if(!valueNodeMap.containsKey(parent)) {
                valueNodeMap.put(parent, new TreeNode(parent));
            }
            if(!valueNodeMap.containsKey(child)) {
                valueNodeMap.put(child, new TreeNode(child));
            }

            TreeNode p = valueNodeMap.get(parent);
            TreeNode c = valueNodeMap.get(child);
            if(isLeft) {
                p.left = c;
            } else {
                p.right = c;
            }
            parents.put(c, p);
        }
        for(int v: valueNodeMap.keySet()) {
            if(!parents.containsKey(valueNodeMap.get(v))) {
                return valueNodeMap.get(v);
            }
        }
        return null;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
