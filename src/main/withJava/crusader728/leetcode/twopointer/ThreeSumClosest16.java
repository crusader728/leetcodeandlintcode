package withJava.crusader728.leetcode.twopointer;

import java.util.Arrays;

public class ThreeSumClosest16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i) {
            int low = i + 1;
            int high = nums.length - 1;
            while(low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if(sum == target) {
                    return target;
                } else if(sum > target) {
                    high--;
                } else {
                    low++;
                }
                if(Math.abs(target - sum) < Math.abs(diff)) {
                    diff = target - sum;
                }
            }
        }
        return target - diff;
    }
}
