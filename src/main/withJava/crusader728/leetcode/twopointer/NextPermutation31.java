package withJava.crusader728.leetcode.twopointer;

public class NextPermutation31 {
    public void nextPermutation(int[] nums) {
        if(nums != null && nums.length != 0) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if(i >= 0) {
                int j = nums.length - 1;
                while(nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i+1);
        }
    }

    private void reverse(int[] nums, int i) {
        int l = i;
        int r = nums.length - 1;
        while(l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
