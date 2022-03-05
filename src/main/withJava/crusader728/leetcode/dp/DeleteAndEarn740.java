package withJava.crusader728.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class DeleteAndEarn740 {
    public int deleteAndEarn(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        Map<Integer, Integer> count = new HashMap<>();
        for(int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        int[] dp = new int[10001];
        for(int i = 0; i < dp.length; ++i) {
            if(i == 0) {
                dp[i] = 0;
            } else if(i == 1) {
                dp[i] = count.getOrDefault(i, 0);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * count.getOrDefault(i, 0));
            }
        }
        return dp[10000];
    }
}
