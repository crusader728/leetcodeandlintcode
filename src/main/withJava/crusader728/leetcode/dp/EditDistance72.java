package withJava.crusader728.leetcode.dp;

public class EditDistance72 {
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) {
            throw new IllegalArgumentException();
        }

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = m; i >= 0; --i) {
            for(int j = n; j >= 0; --j) {
                if(i == m && j == n) {
                    dp[m][n] = 0;
                } else if(i == m) {
                    dp[i][j] = dp[i][j + 1] + 1;
                } else if(j == n) {
                    dp[i][j] = dp[i + 1][j] + 1;
                } else {
                    if(word1.charAt(i) == word2.charAt(j)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        int insertCost = dp[i][j + 1] + 1;
                        int deleteCost = dp[i + 1][j] + 1;
                        int replaceCost = dp[i + 1][j + 1] + 1;
                        dp[i][j] = Math.min(insertCost, Math.min(deleteCost, replaceCost));
                    }
                }
            }
        }
        return dp[0][0];
    }
}
