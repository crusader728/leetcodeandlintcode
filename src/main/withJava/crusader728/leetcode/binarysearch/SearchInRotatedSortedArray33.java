package withJava.crusader728.leetcode.binarysearch;

public class SearchInRotatedSortedArray33 {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] < nums[l]) {
                if(nums[mid] < target && target < nums[l]) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            } else {
                if(nums[mid] < target) {
                    l = mid + 1;
                } else if(nums[mid] >= target && target >= nums[l]) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }
        if(l == nums.length) {
            return -1;
        } else {
            return nums[l] == target ? l : -1;
        }
    }
}
