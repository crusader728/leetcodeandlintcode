package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(candidates);
        Set<List<Integer>> results = new HashSet<>();
        List<Integer> sol = new ArrayList<>();
        backtrack(candidates, 0, target, sol, results);
        return new ArrayList<>(results);
    }

    private void backtrack(int[] candidates, int i, int target, List<Integer> sol, Set<List<Integer>> results) {
        if(target == 0) {
            results.add(new ArrayList<>(sol));
            return;
        }
        if(target < 0) {
            return;
        }
        for(int j = 0; j < candidates.length; ++j) {
            if(j > i && candidates[j] == candidates[j - 1]) {
                continue;
            } else {
                sol.add(candidates[j]);
                backtrack(candidates, j + 1, target - candidates[j], sol, results);
                sol.remove(sol.size() - 1);
            }
        }
    }
}
