package withJava.crusader728.leetcode.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class AllAncestorsOfANodeInDAG2192 {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, Set<Integer>> isChildOf = new HashMap<>();
        for(int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            isChildOf.computeIfAbsent(v, k -> new TreeSet<>()).add(u);
        }


        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < n; ++i) {
            dfs(isChildOf, i, result);
            Collections.sort(result.get(i));
        }
        return result;
        
    }

    private void dfs(Map<Integer, Set<Integer>> isChildOf, int i, List<List<Integer>> result) {
        Set<Integer> visited = new HashSet<>();
        result.add(new ArrayList<>());
        dfsInternal(isChildOf, i, i, visited, result);
    }

    private void dfsInternal(Map<Integer, Set<Integer>> isChildOf, int i, int current, Set<Integer> visited,List<List<Integer>> result) {
        visited.add(current);
        if(current != i) {
            result.get(i).add(current);
        }
        for(int parent: isChildOf.getOrDefault(current, Collections.emptySet())) {
            if(!visited.contains(parent)) {
                dfsInternal(isChildOf, i, parent, visited, result);
            }
        }
    }
}
