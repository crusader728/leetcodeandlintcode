package withJava.crusader728.leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku36 {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) {
            throw new RuntimeException();
        }

        for(int i = 0; i < 9; ++i) {
            boolean result = validateRow(board, i) && validateColumn(board, i) && validateSub(board, i);
            if(result == false) {
                return false;
            }
        }
        return true;
    }

    private boolean validateRow(char[][] board, int r) {
        Set<Integer> check = new HashSet<>();
        for(int i = 0; i < 9; ++i) {
            if(board[r][i] >= '0' && board[r][i] <= '9') {
                int digit = board[r][i] - '0';
                if(check.contains(digit)) {
                    return false;
                } else {
                    check.add(digit);
                }
            }
        }
        return true;
    }

    private boolean validateColumn(char[][] board, int c) {
        Set<Integer> check = new HashSet<>();
        for(int i = 0; i < 9; ++i) {
            if(board[i][c] >= '0' && board[i][c] <= '9') {
                int digit = board[i][c] - '0';
                if(check.contains(digit)) {
                    return false;
                } else {
                    check.add(digit);
                }
            }
        }
        return true;
    }

    private boolean validateSub(char[][] board, int i) {
        Set<Integer> check = new HashSet<>();
        for(int r = 0; r < 3; ++r) {
            for(int c = 0; c < 3; ++c) {
                int row = i / 3 + r;
                int col = i % 3 * 3 + c;
                if(board[row][col] >= '0' && board[row][col] <= '9') {
                    int digit = board[row][col] - '0';
                    if(check.contains(digit)) {
                        return false;
                    } else {
                        check.add(digit);
                    }
                }
            }
        }
        return true;
    }
    
}
