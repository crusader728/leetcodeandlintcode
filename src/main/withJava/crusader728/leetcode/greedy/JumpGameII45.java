package withJava.crusader728.leetcode.greedy;

public class JumpGameII45 {
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int count = 0;
        int currentEnd = 0;
        int currentMax = 0;
        for(int i = 0; i < nums.length - 1; ++i) {
            currentMax = Math.max(currentMax, i + nums[i]);

            if(i == currentEnd) {
                count++;
                currentEnd = currentMax;
            }
        }
        return count;
    }
}
