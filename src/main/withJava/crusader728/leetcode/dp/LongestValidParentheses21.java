package withJava.crusader728.leetcode.dp;

public class LongestValidParentheses21 {
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length()];
        int answer = 0;
        for(int i = 0; i < dp.length; ++i) {
            if(s.charAt(i) == ')') {
                if(i == 0) {
                    dp[i] = 0;
                } else {
                    if(s.charAt(i - 1) == '(') {
                        dp[i] = 2 + ((i - 2 >= 0) ? dp[i - 2] : 0);
                    } else if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}
