package withJava.crusader728.leetcode.dp;

public class MaximumProductSubarray152 {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }

        int max = nums[0];
        int min = nums[0];
        int result = max;
        for(int i = 1; i < nums.length; ++i) {
            int current = nums[i];
            int tempMax = Math.max(current, Math.max(max * current, min * current));
            min = Math.min(current, Math.min(max * current, min * current));
            max = tempMax;
            result = Math.max(result, max);
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumProductSubarray152 maximumProductSubarray152 = new MaximumProductSubarray152();
        System.out.println(maximumProductSubarray152.maxProduct(new int[] {-2, 0, -1}));
    }
}
