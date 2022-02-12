package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        backtrack(n, k, 1, current, result);

        return result;
    }

    private void backtrack(int n, int k, int i, List<Integer> current, List<List<Integer>> result) {
        if(k == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for(int x = i; x <= n; ++x) {
            current.add(x);
            backtrack(n, k - 1, x + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
