package withJava.crusader728.leetcode.dp;

public class ArithmeticSlices413 {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null || nums.length < 3) {
            return 0;
        }

        int[] dp = new int[nums.length];
        int sum = 0;
        for(int i = 0; i < dp.length; ++i) {
            if(i < 2) {
                dp[i] = 0;
            } else {
                if(nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                    dp[i] = dp[i - 1] + 1;
                } else {
                    dp[i] = 0;
                }
            }
            sum += dp[i];
        }
        return sum;
    }
}
