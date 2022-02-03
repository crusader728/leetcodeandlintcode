package withJava.crusader728.leetcode.dp;


public class MaxSubarray53 {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int maxCurrent = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; ++i) {
            maxCurrent = Math.max(nums[i], nums[i] + maxCurrent);
            max = Math.max(max, maxCurrent);
        }
        return max;
    }
}
