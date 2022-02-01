package withJava.crusader728.leetcode.binarysearch;

class FindFirstLastPositionInSortedArray34 {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int lowerBound = lower(nums, target);
        int higherBound = higher(nums, target);

        return new int[] {lowerBound, higherBound};
    }

    private int lower(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) {
                r = mid;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if(nums[l] != target) {
            return -1;
        } else {
            return l;
        }
    }

    private int higher(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if(nums[mid] == target) {
                l = mid;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        if(l >= nums.length || nums[l] != target) {
            return -1;
        } else {
            return l;
        }
    }
}