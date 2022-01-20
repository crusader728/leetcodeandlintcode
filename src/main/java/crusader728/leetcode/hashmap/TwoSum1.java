package java.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for(int i = 0; i < nums.length; ++i) {
            int other = target - nums[i];
            if(seen.containsKey(other)) {
                return new int[]{seen.get(other), i};
            } else {
                seen.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
