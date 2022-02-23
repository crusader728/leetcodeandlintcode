package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph133 {
    private static class Node {
        int val;
        List<Node> neighbors;
    }

    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        Deque<Node> queue = new ArrayDeque<>();
        Map<Node, Node> visited = new HashMap<>();

        queue.offer(node);
        Node newNode = new Node();
        newNode.neighbors = new ArrayList<>();
        newNode.val = node.val;
        visited.put(node, newNode);
        while(!queue.isEmpty()) {
            Node poll = queue.poll();

            for(Node neighbor: poll.neighbors) {
                if(!visited.containsKey(neighbor)) {
                    newNode = new Node();
                    newNode.val = neighbor.val;
                    visited.put(neighbor, newNode);
                    queue.offer(neighbor);
                }
                visited.get(poll).neighbors.add(visited.get(neighbor));
            }
        }
        return visited.get(node);
    }
}
