package withJava.crusader728.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK560 {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for(int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int count = 0;
        Map<Integer, Integer> prefixSumFreq = new HashMap<>();
        for(int i = 0; i < prefixSum.length; ++i) {
            int other = prefixSum[i] - k;
            if(prefixSumFreq.containsKey(other)) {
                count += prefixSumFreq.get(other);
            } 
            prefixSumFreq.put(prefixSum[i], prefixSumFreq.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }
}
