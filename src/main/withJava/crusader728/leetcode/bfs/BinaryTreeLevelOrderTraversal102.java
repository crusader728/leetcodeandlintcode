package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeLevelOrderTraversal102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        int depth = -1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> ans = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode poll = queue.poll();
                if (ans.size() < depth + 1) {
                    ans.add(new ArrayList<>());
                }
                ans.get(depth).add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return ans;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
