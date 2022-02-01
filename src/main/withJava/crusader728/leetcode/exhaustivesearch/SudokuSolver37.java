package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SudokuSolver37 {
    public void solveSudoku(char[][] board) {
        Map<Integer, Map<Integer, Integer>> sol = new HashMap<>();
        Map<Integer, Set<Integer>> rowChecks = new HashMap<>();
        Map<Integer, Set<Integer>> colChecks = new HashMap<>();
        Map<Integer, Set<Integer>> blockChecks = new HashMap<>();
        List<Map<Integer, Map<Integer, Integer>>> results = new ArrayList<>();


        //initialize
        for(int i = 0; i < board.length; ++i) {
            rowChecks.put(i, new HashSet<>());
            colChecks.put(i, new HashSet<>());
            blockChecks.put(i, new HashSet<>());
        }

        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] != '.') {
                    int digit = board[i][j] - '0';
                    rowChecks.get(i).add(digit);
                    colChecks.get(j).add(digit);
                    int blockIdx = i / 3 * 3 + j / 3;
                    blockChecks.get(blockIdx).add(digit);
                }
            }
        }

 
        backtrack(board, 0, 0, sol, rowChecks, colChecks, blockChecks, results);

        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == '.') {
                    board[i][j] = (char)('0' + results.get(0).get(i).get(j));
                }
            }
        }
    }

    private boolean backtrack(char[][] board, int r, int c, 
    Map<Integer, Map<Integer, Integer>> sol, 
    Map<Integer, Set<Integer>> rowCheck, 
    Map<Integer, Set<Integer>> colCheck,
    Map<Integer, Set<Integer>> blockCheck,
    List<Map<Integer, Map<Integer, Integer>>> results) {
        if(r == board.length) {
            Map<Integer, Map<Integer, Integer>> result = new HashMap<>();
            sol.forEach((row, m) -> {
                result.put(row, new HashMap<>());
                m.forEach((col, v) -> {
                    result.get(row).put(col, v);
                });
            });
            results.add(result);
            return true;
        } 
        if(board[r][c] != '.') {
            if(c == board[r].length - 1) {
                return backtrack(board, r + 1, 0, sol, rowCheck, colCheck, blockCheck, results);
            } else {
                return backtrack(board, r, c + 1, sol, rowCheck, colCheck, blockCheck, results);
            }
        } else {
            int blockIdx = r / 3 * 3 + c / 3;
            boolean found = false;
            for(int i = 1; i < 10; ++i) {
                if(found == true) {
                    break;
                }
                if(!(rowCheck.get(r).contains(i) || colCheck.get(c).contains(i) || blockCheck.get(blockIdx).contains(i))) {
                    rowCheck.get(r).add(i);
                    colCheck.get(c).add(i);
                    blockCheck.get(blockIdx).add(i);
                    sol.computeIfAbsent(r, k -> new HashMap<>()).put(c, i);
                    if(c == board[r].length - 1) {
                        found = backtrack(board, r + 1, 0, sol, rowCheck, colCheck, blockCheck, results);
                    } else {
                        found = backtrack(board, r, c + 1, sol, rowCheck, colCheck, blockCheck, results);
                    }
                    rowCheck.get(r).remove(i);
                    colCheck.get(c).remove(i);
                    blockCheck.get(blockIdx).remove(i);
                    sol.get(r).remove(c);
                }
            }
            return found;
        }
    }
}
