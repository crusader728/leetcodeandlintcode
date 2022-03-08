package withJava.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class MostFrequentNumberFollowingKeyInArray2190 {
    public int mostFrequent(int[] nums, int key) {
        Map<Integer, Integer> occurs = new HashMap<>();
        for(int i = 0; i < nums.length - 1; ++i) {
            int k = nums[i];
            int v = nums[i + 1];
            if(k == key) {
                occurs.put(v, occurs.getOrDefault(v, 0) + 1);
            }
        }

        int max = Integer.MIN_VALUE;
        Integer maxV = null;

        for(int v: occurs.keySet()) {
            if(occurs.get(v) > max) {
                max = occurs.get(v);
                maxV = v;
            }
        }
        return maxV;
    }
}
