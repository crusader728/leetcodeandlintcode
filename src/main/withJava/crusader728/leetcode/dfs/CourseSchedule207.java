package withJava.crusader728.leetcode.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseSchedule207 {
    boolean hasCycle = false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int[] edge: prerequisites) {
            int u = edge[0];
            int v = edge[1];
            if(!adj.containsKey(v)) {
                adj.put(v, new HashSet<>());
            }
            adj.get(v).add(u);
        }

        int[] color = new int[numCourses];
        for(int i = 0; i < numCourses; ++i) {
            if(color[i] == 0) {
                dfs(adj, i, color);
            }
        }
        return hasCycle;

    }

    private void dfs(Map<Integer, Set<Integer>> adj, int i, int[] color) {
        color[i] = 1;
        for(int neighbor: adj.getOrDefault(i, Collections.emptySet())) {
            if(color[neighbor] == 0) {
                dfs(adj, neighbor, color);
            } else if(color[neighbor] == 1) {
                hasCycle = true;
            }
        }
        color[i] = 2;
    }

}
