package withJava.crusader728.leetcode.dfs;

import java.util.*;

public class CourseScheduleII210 {
    boolean hasCycle = false;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int[] edge: prerequisites) {
            int u = edge[0];
            int v = edge[1];
            if(!adj.containsKey(v)) {
                adj.put(v, new HashSet<>());
            }
            adj.get(v).add(u);
        }

        List<Integer> result = new ArrayList<>();
        int[] color = new int[numCourses];
        for(int i = 0; i < numCourses; ++i) {
            if(color[i] == 0) {
                dfs(adj, i, color, result);
            }
        }
        if(hasCycle) {
            return new int[0];
        } else {
            int[] topo = new int[numCourses];
            for(int i = result.size() - 1; i >= 0; --i) {
                topo[result.size() - 1 - i] = result.get(i);
            }
            return topo;
        }
    }

    private void dfs(Map<Integer, Set<Integer>> adj, int i, int[] color, List<Integer> result) {
        color[i] = 1;
        for(int neighbor: adj.getOrDefault(i, Collections.emptySet())) {
            if(color[neighbor] == 0) {
                dfs(adj, neighbor, color, result);
            } if(color[neighbor] == 1) {
                hasCycle = true;
            }
        }
        color[i] = 2;
        result.add(i);
    }
}
