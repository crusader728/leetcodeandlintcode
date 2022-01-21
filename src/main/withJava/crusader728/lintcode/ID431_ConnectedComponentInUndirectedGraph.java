package withJava.crusader728.lintcode;

import java.util.*;

public class ID431_ConnectedComponentInUndirectedGraph {
    class Graph {
        List<Integer> V;
        Map<Integer, Set<Integer>> E;
    }

     class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) {
         label = x;
          neighbors = new ArrayList<UndirectedGraphNode>();
      }
  }

    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<>();
        helper(nodes, result);
        return result;
    }

    private void helper(List<UndirectedGraphNode> nodes, List<List<Integer>> result) {
        Graph g = buildGraph(nodes);
        Set<Integer> visited = new HashSet<Integer>();
        for(int i = 0; i < g.V.size(); ++i) {
            if(!visited.contains(g.V.get(i))) {
                Set<Integer> set = bfs(g, g.V.get(i));
                for(Integer n: set) {
                    visited.add(n);
                }
                List<Integer> res = new ArrayList<>(set);
                Collections.sort(res);
                result.add(res);
            }
        }
    }


    private Set<Integer> bfs(Graph g, Integer node) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Integer top = queue.poll();
            visited.add(top);
            for(Integer next: g.E.getOrDefault(top, Collections.emptySet())) {
                if(!visited.contains(next)) {
                    queue.offer(next);
                }
            }
        }
        return visited;
    }

    private Graph buildGraph(List<UndirectedGraphNode> nodes) {
        List<Integer> V = new ArrayList<>();
        Map<Integer, Integer> labelToIndex = new HashMap<>();
        Map<Integer, Set<Integer>> E = new HashMap<>();
        for(UndirectedGraphNode n: nodes) {
            V.add(n.label);
            labelToIndex.put(n.label, V.size() - 1);
        }
        for(UndirectedGraphNode n: nodes) {
            int i = n.label;
            if(n.neighbors != null) {
                for(UndirectedGraphNode neighbor: n.neighbors) {
                    int j = neighbor.label;
                    if(!E.containsKey(i)) {
                        E.put(i, new HashSet<>());
                    }
                    if(!E.containsKey(j)) {
                        E.put(j, new HashSet<>());
                    }
                    E.get(i).add(j);
                    E.get(j).add(i);
                }
            }
        }
        Graph g = new Graph();
        g.V = V;
        g.E = E;
        return g;
    }
}
