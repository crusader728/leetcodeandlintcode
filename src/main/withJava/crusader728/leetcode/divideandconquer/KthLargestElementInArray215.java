package withJava.crusader728.leetcode.divideandconquer;

public class KthLargestElementInArray215 {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || k < 1 || k > nums.length) {
            throw new IllegalArgumentException();
        }

        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int pivot = left;
            int pivotValue = nums[left];
            swap(nums, pivot, right);
            int storeIndex = left;
            for(int i = left; i <= right - 1; ++i) {
                if(nums[i] < pivotValue) {
                    swap(nums, storeIndex, i);
                    storeIndex++;
                }
            }
            swap(nums, right, storeIndex);
            pivot = storeIndex;
            if(pivot + k == nums.length) {
                return nums[pivot];
            } else if(pivot + k < nums.length) {
                left = pivot + 1;
            } else {
                right = pivot + 1;
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int pivot) {
        int temp = nums[i];
        nums[i] = nums[pivot];
        nums[pivot] = temp;
    }
}
