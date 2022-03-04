package withJava.crusader728.leetcode.dp;

public class MaximumProductSubarray152 {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        Integer[] dpPositive = new Integer[nums.length];
        Integer[] dpNegative = new Integer[nums.length];

        for(int i = 0; i < nums.length; ++i) {
            if(i == 0) {
                if(nums[i] >= 0) {
                    dpPositive[i] = nums[i];
                    dpNegative[i] = null;
                } else {
                    dpNegative[i] = nums[i];
                    dpPositive[i] = null;
                }
            } else if(nums[i] >= 0) {
                dpPositive[i] = dpPositive[i - 1] == null ? nums[i] : Math.max(nums[i], dpPositive[i - 1] * nums[i]);
                if(dpNegative[i - 1] == null || dpNegative[i - 1] * nums[i] == 0) {
                    dpNegative[i] = null;
                } else {
                    dpNegative[i] = dpNegative[i - 1] * nums[i];
                }
            } else {
                dpPositive[i] = dpNegative[i - 1] == null ? null : dpNegative[i - 1] * nums[i];
                if(dpPositive[i - 1] == null || dpPositive[i - 1] * nums[i] == 0) {
                    dpNegative[i] = nums[i];
                } else {
                    dpNegative[i] = dpPositive[i - 1] * nums[i];
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ++i) {
            if(dpPositive[i] != null) {
                max = Math.max(max, dpPositive[i]);
            } 
            if(dpNegative[i] != null) {
                max = Math.max(max, dpNegative[i]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumProductSubarray152 maximumProductSubarray152 = new MaximumProductSubarray152();
        System.out.println(maximumProductSubarray152.maxProduct(new int[] {-2, 0, -1}));
    }
}
