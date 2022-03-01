package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumWidthOfBinaryTree662 {
    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Deque<Info> queue = new ArrayDeque<>();
        queue.offer(new Info(root, 1));
        Long max = 0L;
        while(!queue.isEmpty()) {
            int size = queue.size();
            long localMin = Long.MAX_VALUE;
            long localMax = Long.MIN_VALUE;
            for(int i = 0; i < size; ++i) {
                Info poll = queue.poll();
                localMin = Math.min(poll.id, localMin);
                localMax = Math.max(poll.id, localMax);
                max = Math.max(localMax - localMin + 1, max);
                if(poll.node.left != null) {
                    queue.offer(new Info(poll.node.left, poll.id * 2));
                }
                if(poll.node.right != null) {
                    queue.offer(new Info(poll.node.right, poll.id * 2 + 1));
                }
            }
        }
        return Long.valueOf(max).intValue();
        
    }

    private static class Info {
        TreeNode node;
        long id;
        Info(TreeNode node, long id) {
            this.node = node;
            this.id = id;
        }
    }


    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
