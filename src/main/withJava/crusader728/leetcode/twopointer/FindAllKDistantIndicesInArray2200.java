package withJava.crusader728.leetcode.twopointer;

import java.util.ArrayList;
import java.util.List;

public class FindAllKDistantIndicesInArray2200 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        boolean[] check = new boolean[nums.length];
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == key) {
                for(int j = Math.max(0, i - k); j <= Math.min(i + k, nums.length - 1); ++j) {
                    check[j] = true;
                }
            }
        }
        for(int i = 0; i < check.length; ++i) {
            if(check[i]) {
                result.add(i);
            }
        }
        return result;
    }
}
