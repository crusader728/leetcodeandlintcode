package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        backtrack(nums, 0, sol, results);
        return results;
    }

    private void backtrack(int[] nums, int i, List<Integer> sol, List<List<Integer>> results) {
        if(i == nums.length) {
            results.add(new ArrayList<>(sol));
        } else {
            Set<Integer> seen = new HashSet<>();
            for(int j = i; j < nums.length; ++j) {
                if(seen.add(nums[j])) {
                    swap(nums, i, j);
                    sol.add(nums[i]);
                    backtrack(nums, i + 1, sol, results);
                    sol.remove(sol.size() - 1);
                    swap(nums, i, j);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
