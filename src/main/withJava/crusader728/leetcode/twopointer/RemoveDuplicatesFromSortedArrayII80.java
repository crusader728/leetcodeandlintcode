package withJava.crusader728.leetcode.twopointer;

public class RemoveDuplicatesFromSortedArrayII80 {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int pivot = 0;
        int i = 0;
        while(i < nums.length) {
            int count = 0;
            while(i < nums.length && count < 2 && nums[pivot] == nums[i]) {
                i++;
                count++;
            }
            for(int j = 0; j < count && pivot + j < nums.length; ++j) {
                nums[pivot + j] = nums[pivot];
            }
            if(i == nums.length) {
                pivot = pivot + count;
            } else if(nums[i] != nums[pivot]) {
                pivot = pivot + count;
                nums[pivot] = nums[i];
            } else {
                while(i < nums.length && nums[i] == nums[pivot]) {
                    i++;
                }
                pivot = pivot + 2;
                if(i == nums.length) {
                    continue;
                } else {
                    nums[pivot] = nums[i];
                }
            }
        }
        return pivot;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArrayII80 removeDuplicatesFromSortedArrayII80 = new RemoveDuplicatesFromSortedArrayII80();
        removeDuplicatesFromSortedArrayII80.removeDuplicates(new int[] {0,0,1,1,1,1,2,3,3});
    }
}
