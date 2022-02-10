package withJava.crusader728.leetcode.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }   

        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;
        for(int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for(int i = 0; i < prefixSum.length; ++i) {
            if(i == 0) {
                map.put(prefixSum[i], 1);
            } else {
                int other = prefixSum[i] - k;
                if(map.containsKey(other)) {
                    count += map.get(other);
                } 
                map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
            }
        }
        return count;
    }
}
