package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        backtrack(candidates, 0, target, sol, result);
        return result;
    }

    private void backtrack(int[] candidates, int i, int target, List<Integer> sol, List<List<Integer>> result) {
        if(i == candidates.length && target == 0) {
            ArrayList<Integer> r = new ArrayList<>();
            sol.forEach(x -> r.add(x));
            result.add(r);
        } else if(i == candidates.length && target != 0) {
            return;
        } else {
            if(target >= candidates[i]) {
                sol.add(candidates[i]);
                backtrack(candidates, i, target - candidates[i], sol, result);
                sol.remove(sol.size() - 1);
            }
            backtrack(candidates, i + 1, target, sol, result);
        }
    }
}
