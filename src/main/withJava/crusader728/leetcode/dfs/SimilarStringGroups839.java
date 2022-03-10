package withJava.crusader728.leetcode.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimilarStringGroups839 {
    public int numSimilarGroups(String[] strs) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int i = 0; i < strs.length; ++i) {
            for(int j = i + 1; j < strs.length; ++j) {
                if(isSimilar(strs[i], strs[j])) {
                    adj.computeIfAbsent(i, k -> new HashSet<>()).add(j);
                    adj.computeIfAbsent(j, k -> new HashSet<>()).add(i);
                }
            }
        }

        int count = 0;
        boolean[] visited = new boolean[strs.length];
        for(int i = 0; i < strs.length; ++i) {
            if(!visited[i]) {
                count++;
                dfs(adj, i, visited);
            }
        }
        return count;
    }

    private void dfs(Map<Integer, Set<Integer>> adj, int i, boolean[] visited) {
        visited[i] = true;
        for(int neighbor: adj.getOrDefault(i, Collections.emptySet())) {
            if(!visited[neighbor]) {
                dfs(adj, neighbor, visited);
            }
        }
    }

    private boolean isSimilar(String string, String string2) {
        int diffCount = 0;
        for(int i = 0; i < string.length(); ++i) {
            if(string.charAt(i) != string2.charAt(i)) {
                diffCount++;
            }
            if(diffCount > 2) {
                return false;
            }
        }
        return diffCount == 2 || diffCount == 0;
    }
}
