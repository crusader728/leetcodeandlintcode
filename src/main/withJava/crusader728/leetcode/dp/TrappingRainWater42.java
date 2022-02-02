package withJava.crusader728.leetcode.dp;

public class TrappingRainWater42 {
    public int trap(int[] height) {
        if(height == null || height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        for(int i = 0; i < height.length; ++i) {
            if(i == 0) {
                leftMax[i] = 0;
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
            }
        }
        for(int i = height.length - 1; i >= 0; i--) {
            if(i == height.length - 1) {
                rightMax[i] = 0;
            } else {
                rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
            }
        }

        int sum = 0;
        for(int i = 0; i < height.length; ++i) {
            int h = Math.min(leftMax[i], rightMax[i]);
            if(height[i] < h) {
                sum += h - height[i];
            }
        }
        return sum;
    }
}
