package withJava.crusader728.leetcode.twopointer;

import java.util.ArrayDeque;
import java.util.Deque;

public class LowestCommonAncestorOfBinaryTreeIII1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Deque<Node> pathP = getPath(p);
        Deque<Node> pathQ = getPath(q);
        Node prev = null;
        while(!pathQ.isEmpty() && !pathP.isEmpty() && pathP.peekFirst() == pathQ.peekFirst()) {
            prev = pathP.poll();
            pathQ.poll();
        }
        return prev;
    }

    private Deque<Node> getPath(Node q) {
        Deque<Node> result = new ArrayDeque<>();
        while(q != null) {
            result.push(q);
            q = q.parent;
        }
        return result;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }
}
