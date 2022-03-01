package withJava.crusader728.leetcode.dp;

public class CountingBits338 {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            if(i == 0) {
                dp[i] = 0;
            } else {
                dp[i] = dp[i >> 1] + i % 2;
            }
        }
        return dp;
    }
}
