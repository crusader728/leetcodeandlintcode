package withJava.crusader728.leetcode.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;


public class GraphValidTree261 {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) {
            return false;
        }

        //build graph
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int i = 0; i < edges.length; ++i) {
            int s = edges[i][0];
            int e = edges[i][1];

            if(!adj.containsKey(s)) {
                adj.put(s, new HashSet<>());
            }
            if(!adj.containsKey(e)) {
                adj.put(e, new HashSet<>());
            }
            adj.get(s).add(e);
            adj.get(e).add(s);
        }

        int[] color = new int[n];
        boolean hasCycle = dfs(adj, 0, color);
        if(!hasCycle) {
            return IntStream.range(0, n).noneMatch(i -> color[i] == 0);
        } else {
            return false;
        }
    }

    private boolean dfs(Map<Integer, Set<Integer>> adj, int i, int[] color) {
        if(color[i] == 0) {
            color[i] = 1;
            boolean hasCycle = false;
            for(Integer neighbor: adj.getOrDefault(i, Collections.emptySet())) {
                if(color[neighbor] == 2) {
                    return true;
                } else if(color[neighbor] == 0) {
                    hasCycle = dfs(adj, neighbor, color);
                    if(hasCycle) {
                        return hasCycle;
                    }
                }
            }
            color[i] = 2;
            return hasCycle;
        } else {
            return false;
        }
    }

}
