package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeRightSideView199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        } else {
            Deque<TreeNode> queue = new ArrayDeque<>();
            queue.offer(root);
            int count = -1;
            while(!queue.isEmpty()) {
                int size = queue.size();
                count++;
                for(int i = 0; i < size; ++i) {
                    TreeNode poll = queue.poll();
                    if(result.size() < count + 1) {
                        result.add(poll.val);
                    } else {
                        result.set(count, poll.val);
                    }
                    if(poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if(poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            return result;
        }
    }

    private static final class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
