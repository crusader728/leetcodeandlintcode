package withJava.crusader728.leetcode.bfs;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal314 {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }

        Queue<Integer> columnQueue = new ArrayDeque<>();
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        TreeMap<Integer, List<Integer>> result = new TreeMap<>();
        columnQueue.offer(0);
        nodeQueue.offer(root);
        while(!nodeQueue.isEmpty()) {
            int column = columnQueue.poll();
            TreeNode node = nodeQueue.poll();
            result.computeIfAbsent(column, k -> new ArrayList<>()).add(node.val);
            if(node.left != null) {
                columnQueue.offer(column - 1);
                nodeQueue.offer(node.left);
            }
            if(node.right != null) {
                columnQueue.offer(column + 1);
                nodeQueue.offer(node.right);
            }
        }
        List<List<Integer>> r = new ArrayList<>();
        result.forEach((k, v) -> r.add(v));
        return r;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
