package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class PopulatingNextRightPointerInEachNode116 {
    public Node connect(Node root) {
        if(root == null) {
            return null;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level = queue.size();
            for(int i = 0; i < level; ++i) {
                Node node = queue.poll();
                if(i == level - 1) {
                    node.next = queue.peekFirst();
                } else {
                    node.next = null;
                }
                if(node.left != null) {
                    queue.offer(node.left);
                } 
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node next;
    }
}
