package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxAreaOfIsland695 {
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) {
            throw new IllegalArgumentException();
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int area = 0;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 1) {
                    area = Math.max(area, bfs(grid, m, n, i, j, visited));
                }
            }
        }

        return area;
    }

    private int bfs(int[][] grid, int m, int n, int i, int j, boolean[][] visited) {
        Deque<Integer> queue = new ArrayDeque<>();   
        queue.offer(i * n + j);
        visited[i][j] = true;
        int count = 1;
        while(!queue.isEmpty()) {
            int top = queue.poll();
            int x = top / n;
            int y = top % n;
            for(int k = 0; k < deltas.length; ++k) {
                int r = x + deltas[k][0];
                int c = y + deltas[k][1];
                if(r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1 && !visited[r][c]) {
                    queue.offer(r * n + c);
                    visited[r][c] = true;
                    count++;
                }
            }
        }
        return count;
    }

    private static int[][] deltas = new int[][] {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
}
