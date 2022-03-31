package withJava.crusader728.leetcode.twopointer;

import java.util.Arrays;

public class ValidTriangleNumber611 {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        int i = 0;
        while(i < nums.length - 2) {
            int j = i + 1;
            int k = i + 2;
            while(j < nums.length - 1 && nums[i] != 0) {
                while(k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                count += k - j - 1;
                j++;
            }
            i++;
        }
        return count;
    }
}
