package withJava.crusader728.leetcode.unionfind;

public class NumberOfIslands200 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] parents = new int[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1') {
                    parents[i][j] = i * n + j;
                    count++;
                } else {
                    parents[i][j] = -1;
                }
            }
        }

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1') {
                    for(int k = 0; k < 4; ++k) {
                        int r = i + deltas[k][0];
                        int c = j + deltas[k][1];
                        if(r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1') {
                            int root1 = find(m, n, i, j, parents);
                            int root2 = find(m, n, r, c, parents);
                            if(root1 != root2) {
                                int smaller = Math.min(root1, root2);
                                int larger = Math.max(root1, root2);
                                parents[larger / n][larger % n] = parents[smaller / n][smaller % n];
                                count--;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private int find(int m, int n, int i, int j, int[][] parents) {
        if(parents[i][j] == i * n + j) {
            return i * n + j;
        } else {
            int root = find(m, n, parents[i][j] / n, parents[i][j] % n, parents);
            parents[i][j] = root;
            return root;
        }
    }

    private static int[][] deltas = new int[][] {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };
}
