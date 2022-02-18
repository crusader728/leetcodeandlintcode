package withJava.crusader728.leetcode.dp;

public class DecodeWays91 {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        for(int i = s.length(); i >= 0; --i) {
            if(i == s.length()) {
                dp[i] = 1;
            } else if(s.charAt(i) == '0') {
                dp[i] = 0;
            } else if(i == s.length() - 1) {
                dp[i] = 1;
            } else if(s.charAt(i) != '1' && s.charAt(i) != '2') {
                dp[i] = dp[i + 1];
            } else if(s.charAt(i) == '1') {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else if(s.charAt(i) == '2' && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '6') {
                dp[i] = dp[i + 1] + dp[i + 2];
            } else {
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];

    }
}
