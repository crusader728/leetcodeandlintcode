package withJava.crusader728.leetcode.dfs;

import java.util.HashMap;
import java.util.Map;

public class CopyListWIthRandomPointer138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> seen = new HashMap<>();
        return dfs(head, seen);
    }

    private Node dfs(Node head, Map<Node, Node> seen) {
        if(head == null) {
            return null;
        }

        if(seen.containsKey(head)) {
            return seen.get(head);
        }

        Node node = new Node();
        seen.put(head, node);
        node.next = dfs(head.next, seen);
        node.random = dfs(head.random, seen);
        return node;
    }

    private static class Node {
        int val;
        Node next;
        Node random;
    }
}
