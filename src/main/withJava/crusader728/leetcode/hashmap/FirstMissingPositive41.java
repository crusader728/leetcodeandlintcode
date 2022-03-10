package withJava.crusader728.leetcode.hashmap;

public class FirstMissingPositive41 {
    public int firstMissingPositive(int[] nums) {
        int l = moveAllNegatives(nums);
        if(l >= nums.length) {
            return 1;
        } else {
            //l-th to nums.length -1 th element is all positive
            for(int i = l; i < nums.length; i++) {
                int x = nums[i] < 0 ? -1 * nums[i] : nums[i];
                if(x > nums.length - l) {
                    continue;
                } else {
                    int key = x + l - 1;
                    if(nums[key] > 0) {
                        nums[key] *= -1;
                    }
                }
            }
            for(int i = l; i < nums.length; i++) {
                if(nums[i] > 0) {
                    return i - l + 1;
                } 
            }
            return nums.length - l + 1;
        }
    }

    private int moveAllNegatives(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l <= r) {
            while(l < nums.length && nums[l] <= 0) {
                l++;
            }
            while(r >= 0 && nums[r] > 0) {
                r--;
            }
            if(l < nums.length && r >= 0 && l <= r) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            } else {
                break;
            }
        }
        return l;
    }
}
