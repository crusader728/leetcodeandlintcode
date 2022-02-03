package withJava.crusader728.leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class KeepMultiplyingFoundValuesByTwo2154 {
    public int findFinalValue(int[] nums, int original) {
        if(nums == null) {
            throw new IllegalArgumentException();
        }   

        Set<Integer> set = new HashSet<>();
        for(int n: nums) {
            set.add(n);
        }
        int ans = original;
        while(set.contains(ans)) {
            ans = ans * 2;
        }
        return ans;
    }
}
