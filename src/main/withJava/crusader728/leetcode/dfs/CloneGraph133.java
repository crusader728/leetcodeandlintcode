package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph133 {
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    private Node dfs(Node node, Map<Node, Node> visited) {
        if(node == null) {
            return null;
        }
        if(visited.containsKey(node)) {
            return visited.get(node);
        }

        Node clone = new Node(node.val);
        visited.put(node, clone);
        for(Node n: node.neighbors) {
            clone.neighbors.add(dfs(n, visited));
        }
        return clone;
    }

    private static class Node {
        int val;
        List<Node> neighbors;

        Node(int _val) {
            this.val = _val;
            this.neighbors = new ArrayList<>();
        }

        Node(int _val, List<Node> _neighbors) {
            this.val = _val;
            this.neighbors = _neighbors;
        }
    }
}
