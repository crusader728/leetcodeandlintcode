package withJava.crusader728.leetcode.prefixsum;

import java.util.Random;

public class RandomPickWithWeight528 {
    private int totalSum;
    private int[] prefixSum;
    private Random random;
    public RandomPickWithWeight528(int[] w) {
        prefixSum = new int[w.length];
        totalSum = 0;
        for(int i = 0; i < w.length; ++i) {
            totalSum += w[i];
            prefixSum[i] = totalSum;
        }
        random = new Random();
    }

    public int pickIndex() {
        int rng = random.nextInt(totalSum);
        int low = 0;
        int high = prefixSum.length - 1;
        while(low < high) {
            int mid = low + (high - low) / 2;
            int w = prefixSum[mid];
            if(rng < w) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
