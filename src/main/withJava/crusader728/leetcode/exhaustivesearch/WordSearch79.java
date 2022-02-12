package withJava.crusader728.leetcode.exhaustivesearch;

public class WordSearch79 {
    public boolean exist(char[][] board, String word) {
        if(board == null) {
            return false;
        }
        
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                if(board[i][j] == word.charAt(0)) {
                    boolean found = backtrack(board, word, i, j, 0, visited);
                    if(found) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String word, int i, int j, int k, boolean[][] visited) {
        if(board[i][j] != word.charAt(k)) {
            return false;
        }
        if(k == word.length() - 1) {
            return true;
        }

        visited[i][j] = true;
        boolean found = false;
        for(int[] delta: deltas) {
            if(!found) {
                int r = i + delta[0];
                int c = j + delta[1];
                if(r >= 0 && r < board.length && c >= 0 && c < board[r].length && !visited[r][c]) {
                    found = backtrack(board, word, r, c, k + 1, visited);
                }
            } else {
                break;
            }
        }
        visited[i][j] = false;
        return found;
    }

    private int[][] deltas = new int[][] {
        {0, 1},
        {0, -1},
        {-1, 0},
        {1, 0}
    };

    public static void main(String[] args) {
        WordSearch79 wordSearch79 = new WordSearch79();
        boolean abceseeefs = wordSearch79.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCESEEEFS");
        System.out.println(abceseeefs);
    }
}
