package withJava.crusader728.leetcode.twopointer;

public class SortColors75 {
    public void sortColors(int[] nums) {
        if (nums == null) {
            return;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            while (r >= 0 && nums[r] > 1) {
                r--;
            }
            if (l <= r) {
                int t = nums[r];
                nums[r] = nums[l];
                nums[l] = t;
                l++;
            } else {
                break;
            }
        }
        l = 0;
        while (l <= r) {
            while (l < nums.length && nums[l] < 1) {
                l++;
            }
            if (l <= r) {
                int t = nums[l];
                nums[l] = nums[r];
                nums[r] = t;
                r--;
            } else {
                break;
            }
        }
    }
}
