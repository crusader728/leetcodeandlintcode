package withJava.crusader728.leetcode.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>();
        dict.addAll(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for(int i = s.length() - 1; i >= 0; --i) {
            for(int j = i + 1; j < s.length(); ++j) {
                String sub = s.substring(i, j);
                if(dict.contains(sub)) {
                    dp[i] = dp[i] || dp[j];
                }
                if(dp[i]) {
                    break;
                }
            }
        }
        return dp[0];
    }
}
