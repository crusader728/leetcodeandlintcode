package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SynonymousSentences1258 {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, Set<String>> adj = new HashMap<>();
        for(List<String> synonym: synonyms) {
            String x = synonym.get(0);
            String y = synonym.get(1);
            adj.computeIfAbsent(x, k -> new HashSet<>()).add(y);
            adj.computeIfAbsent(x, k -> new HashSet<>()).add(x);
            adj.computeIfAbsent(y, k -> new HashSet<>()).add(x);
            adj.computeIfAbsent(y, k -> new HashSet<>()).add(y);
        }

        List<String> result = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        String[] words = text.split(" ");
        backtrack(words, 0, adj, cur, result);
        return result;
    }

    private void backtrack(String[] words, int i, Map<String, Set<String>> adj, List<String> cur, List<String> result) {
        if(i == words.length) {
            result.add(String.join(" ", cur));
            return;
        }

        if(adj.containsKey(words[i])) {
            TreeSet<String> synonyms = new TreeSet<>();
            dfs(adj, words[i], synonyms);
            for(String synonym: synonyms) {
                cur.add(synonym);
                backtrack(words, i + 1, adj, cur, result);
                cur.remove(cur.size() - 1);
            }
        } else {
            cur.add(words[i]);
            backtrack(words, i + 1, adj, cur, result);
            cur.remove(cur.size() - 1);
        }
    }

    private void dfs(Map<String, Set<String>> adj, String string, Set<String> synonyms) {
        for(String neighbor: adj.getOrDefault(string, Collections.emptySet())) {
            if(!synonyms.contains(neighbor)) {
                synonyms.add(neighbor);
                dfs(adj, neighbor, synonyms);
            }
        }
    }
}
