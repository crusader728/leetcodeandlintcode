package withJava.crusader728.lintcode;

import java.util.*;

public class ID1210_FindSubsequences {
    /**
     * @param nums: an integer array
     * @return: all the different possible increasing subsequences of the given array
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for(int i = 0; i < nums.length; ++i) {
            helper(nums, i, new ArrayList<>(), result);
        }
        List<List<Integer>> sol = new ArrayList<>(result);
        return sol;
    }

    private void helper(int[] nums, int i, ArrayList<Integer> current, Set<List<Integer>> result) {
        current.add(nums[i]);
        if(current.size() >= 2) {
            result.add(new ArrayList<>(current));
        }
        for(int j = i + 1; j < nums.length; ++j) {
            if(nums[j] >= nums[i]) {
                helper(nums, j, current, result);
            }
        }
        current.remove(current.size() - 1);
    }
}
