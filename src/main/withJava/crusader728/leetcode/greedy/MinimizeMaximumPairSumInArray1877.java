package withJava.crusader728.leetcode.greedy;

import java.util.Arrays;

public class MinimizeMaximumPairSumInArray1877 {
    public int minPairSum(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = Integer.MIN_VALUE;
        int l = 0, r = nums.length - 1;
        while(l < r) {
            result = Math.max(nums[l] + nums[r], result);
            l++;
            r--;
        }
        return result;
    }
}
