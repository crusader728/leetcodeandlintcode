package java.crusader728.lintcode;

public class ID191_MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int[] maxProduct = new int[nums.length];
        int[] minProduct = new int[nums.length];

        maxProduct[nums.length - 1] = nums[nums.length - 1];
        minProduct[nums.length - 1] = nums[nums.length - 1];

        for(int i = nums.length - 2; i >= 0; --i) {
            maxProduct[i] = Math.max(nums[i], Math.max(nums[i] * maxProduct[i + 1], nums[i] * minProduct[i + 1]));
            minProduct[i] = Math.min(nums[i], Math.min(nums[i] * maxProduct[i + 1], nums[i] * minProduct[i + 1]));
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; ++i) {
            max = Math.max(max, maxProduct[i]);
        }
        return max;
    }


}
