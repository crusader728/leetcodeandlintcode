package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.*;

public class WordBreakII140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, dict, 0, memo);
    }

    private List<String> dfs(String s, Set<String> dict, int i, Map<Integer, List<String>> memo) {
        if(i == s.length()) {
            return Collections.singletonList("");
        }
        if(memo.containsKey(i)) {
            return memo.get(i);
        }

        List<String> result = new ArrayList<>();
        for(int j = i + 1; j <= s.length(); ++j) {
            String sub = s.substring(i, j);
            if(dict.contains(sub)) {
                List<String> remaining = dfs(s, dict, j, memo);
                for(String rem: remaining) {
                    if(rem.isEmpty()) {
                        result.add(sub);
                    } else {
                        result.add(String.format("%s %s", sub, rem));
                    }
                }
            }
        }
        memo.put(i, result);
        return result;
    }
}
