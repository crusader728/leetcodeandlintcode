package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.List;

public class Permutations46 {
    public List<List<Integer>> permute(int[] nums) {
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
            return;
        }

        for(int j = i; j < nums.length; ++j) {
            swap(nums, i, j);
            sol.add(nums[i]);
            backtrack(nums, i + 1, sol, results);
            sol.remove(sol.size() - 1);
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
