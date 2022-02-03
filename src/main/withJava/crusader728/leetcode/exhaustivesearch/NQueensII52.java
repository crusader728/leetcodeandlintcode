package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueensII52 {
    public int totalNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        Set<Integer> colCheck = new HashSet<>();
        Set<Integer> diagCheck = new HashSet<>();
        Set<Integer> antiDiagCheck = new HashSet<>();

        backtrack(n, 0, colCheck, diagCheck, antiDiagCheck, sol, result);
        return result.size();
    }

    private void backtrack(int n, int i, Set<Integer> colCheck, Set<Integer> diagCheck, Set<Integer> antiDiagCheck,
            List<Integer> sol, List<List<Integer>> result) {
                if(i == n) {
                    result.add(new ArrayList<>(sol));
                    return;
                }
                for(int c = 0; c < n; ++c) {
                    if(!(colCheck.contains(c) || diagCheck.contains(c + i) || antiDiagCheck.contains(i - c))) {
                        colCheck.add(c);
                        diagCheck.add(c + i);
                        antiDiagCheck.add(i - c);
                        sol.add(c);
                        backtrack(n, i + 1, colCheck, diagCheck, antiDiagCheck, sol, result);
                        sol.remove(sol.size() - 1);
                        antiDiagCheck.remove(i - c);
                        diagCheck.remove(c + i);
                        colCheck.remove(c);
                    }
                }
    }
}
