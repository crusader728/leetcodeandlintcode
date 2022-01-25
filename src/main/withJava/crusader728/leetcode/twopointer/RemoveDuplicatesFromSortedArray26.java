package withJava.crusader728.leetcode.twopointer;

public class RemoveDuplicatesFromSortedArray26 {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int pivot = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(i != 0) {
                if(nums[i] != nums[i - 1]) {
                    nums[pivot++] = nums[i - 1];
                }
            }
        }
        nums[pivot++] = nums[nums.length - 1];
        return pivot;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray26 removeDuplicatesFromSortedArray26 = new RemoveDuplicatesFromSortedArray26();
        System.out.println(removeDuplicatesFromSortedArray26.removeDuplicates(new int[] {1, 1, 2}));
    }
}
