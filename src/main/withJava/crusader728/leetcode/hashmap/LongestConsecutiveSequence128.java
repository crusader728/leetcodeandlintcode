package withJava.crusader728.leetcode.hashmap;

import java.util.*;

public class LongestConsecutiveSequence128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int n: nums) {
            numSet.add(n);
        }
        int ans = 0;
        for(int n: numSet) {
            if(!numSet.contains(n - 1)) {
                int currentNumber = n;
                int currentCount = 1;
                while(numSet.contains(currentNumber + 1)) {
                    currentNumber += 1;
                    currentCount += 1;
                }
                ans = Math.max(ans, currentCount);
            }
        }
        return ans;
    }
}
