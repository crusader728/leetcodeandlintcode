package withJava.crusader728.leetcode.twopointer;

public class RemoveElement27 {
    public int removeElement(int[] nums, int val) {
        int pivot = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] != val) {
                nums[pivot++] = nums[i];
            }
        }
        return pivot;
    }
}
