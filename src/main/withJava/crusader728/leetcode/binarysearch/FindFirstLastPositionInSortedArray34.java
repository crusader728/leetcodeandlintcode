package withJava.crusader728.leetcode.binarysearch;

class FindFirstLastPositionInSortedArray34 {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int lowerBound = lower(nums, target);
        int higherBound = upper(nums, target);

        if(nums[lowerBound] != target) {
            return new int[] {-1, -1};
        } else {
            return new int[] {lowerBound, higherBound - 1};
        }
    }

    private int lower(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int upper(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }   
        return l;
    }
}