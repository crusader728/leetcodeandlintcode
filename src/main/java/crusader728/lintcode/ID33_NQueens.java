package java.crusader728.lintcode;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ID33_NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> horizontal = new HashSet<>();
        Set<Integer> vertical = new HashSet<>();
        Set<Integer> diagonal = new HashSet<>();
        Set<Integer> antiDiagonal = new HashSet<>();
        helper(n, 0, horizontal, vertical, diagonal, antiDiagonal, new ArrayList<Integer>(), result);
        return convert(result, n);
    }

    private void helper(int n, int row, Set<Integer> horizontal, Set<Integer> vertical, Set<Integer> diagonal, Set<Integer> antiDiagonal, List<Integer> current, List<List<Integer>> result) {
        if(row == n) {
            ArrayList<Integer> sol = new ArrayList<>();
            sol.addAll(current);
            result.add(sol);
        } else {
            for(int col = 0; col < n; ++col) {
                if(!horizontal.contains(row) && !vertical.contains(col) && !diagonal.contains(row + col) && !antiDiagonal.contains(row - col)) {
                    current.add(col);
                    vertical.add(col);
                    horizontal.add(row);
                    antiDiagonal.add(row - col);
                    diagonal.add(row + col);
                    helper(n, row + 1, horizontal, vertical, diagonal, antiDiagonal, current, result);
                    vertical.remove(col);
                    horizontal.remove(row);
                    antiDiagonal.remove(row - col);
                    diagonal.remove(row + col);
                    current.remove(current.size() - 1);
                }
            }
        }
    }

    private List<List<String>> convert(List<List<Integer>> pos, int n) {
        List<List<String>> result = new ArrayList<>();
        for(List<Integer> sol: pos) {
            List<String> r = new ArrayList<>();
            for(Integer p: sol) {
                StringBuilder builder = new StringBuilder();
                for(int i = 0; i < n; ++i) {
                    if(i == p) {
                        builder.append('Q');
                    } else {
                        builder.append('.');
                    }
                }
                r.add(builder.toString());
            }
            result.add(r);
        }
        return result;
    }
}
