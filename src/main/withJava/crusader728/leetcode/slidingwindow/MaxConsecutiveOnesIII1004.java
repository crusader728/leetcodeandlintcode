package withJava.crusader728.leetcode.slidingwindow;

public class MaxConsecutiveOnesIII1004 {
    public int longestOnes(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        if(k >= nums.length) {
            return nums.length;
        }
        int l = 0;
        int r = 0;
        int quota = k;
        int max = Integer.MIN_VALUE;
        while(l < nums.length) {
            while(r < nums.length) {
                if(nums[r] == 1) {
                    r++;
                } else if(quota > 0) {
                    r++;
                    quota--;
                } else {
                    break;
                }
            }
            max = Math.max(r - l, max);
            l++;
            if(r < l) {
                r = l;
            }
            if(nums[l - 1] != 1 && quota < k) {
                quota++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxConsecutiveOnesIII1004 maxConsecutiveOnesIII1004 = new MaxConsecutiveOnesIII1004();
        maxConsecutiveOnesIII1004.longestOnes(new int[] {0,0,1,1,1,0,0}, 0);
    }
}
