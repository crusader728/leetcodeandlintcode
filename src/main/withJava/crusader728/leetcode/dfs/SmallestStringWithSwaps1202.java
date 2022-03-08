package withJava.crusader728.leetcode.dfs;

import java.util.*;

public class SmallestStringWithSwaps1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (List<Integer> pair : pairs) {
            int x = pair.get(0);
            int y = pair.get(1);
            adj.computeIfAbsent(x, k -> new HashSet<>()).add(x);
            adj.get(x).add(y);
            adj.computeIfAbsent(y, k -> new HashSet<>()).add(y);
            adj.get(y).add(x);
        }

        char[] result = new char[s.length()];
        boolean[] visited = new boolean[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            if (adj.containsKey(i)) {
                if (!visited[i]) {
                    TreeSet<Integer> clique = new TreeSet<>();
                    dfs(adj, i, clique);
                    List<Character> chars = new ArrayList<>();
                    clique.forEach(x -> {
                        visited[x] = true;
                        chars.add(s.charAt(x));
                    });
                    Collections.sort(chars);
                    Iterator<Integer> iterator = clique.iterator();
                    Iterator<Character> iterator2 = chars.iterator();
                    while (iterator.hasNext()) {
                        result[iterator.next()] = iterator2.next();
                    }
                }
            } else {
                result[i] = s.charAt(i);
            }
        }
        return String.valueOf(result);

    }

    private void dfs(Map<Integer, Set<Integer>> adj, int i, TreeSet<Integer> clique) {
        for (Integer neighbor : adj.getOrDefault(i, Collections.emptySet())) {
            if (!clique.contains(neighbor)) {
                clique.add(neighbor);
                dfs(adj, neighbor, clique);
            }
        }
    }
}
