package withJava.crusader728.leetcode.exhaustivesearch;

public class UniquePathsIII980 {
    public int uniquePathsIII(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int quota = 0;
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[i].length; ++j) {
                if(grid[i][j] == 0) {
                    quota++;
                }
            }
        }

        return search(grid, visited, quota);
    }

    private int search(int[][] grid, boolean[][] visited, int quota) {
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[i].length; ++j) {
                if(grid[i][j] == 1) {
                    return dfs(grid, i, j, visited, quota);
                }
            }
        }
        return 0;
    }

    private int dfs(int[][] grid, int r, int c, boolean[][] visited, int quota) {
        if(grid[r][c] == 2) {
            if(quota == 0) {
                return 1;
            } else {
                return 0;
            }
        } else if(grid[r][c] == 0 || grid[r][c] == 1) {
            visited[r][c] = true;
            int count = 0;
            for(int i = 0; i < dx.length; ++i) {
                int x = r + dx[i];
                int y = c + dy[i];
                if(x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && !visited[x][y]) {
                    count += dfs(grid, x, y, visited, grid[r][c] == 0 ? quota - 1 : quota);
                }
            }
            visited[r][c] = false;
            return count;
        } else {
            return 0;
        }
    }

    private int[] dx = new int[]{0, 0, -1, 1};
    private int[] dy = new int[]{-1, 1, 0, 0};

    public static void main(String[] args) {
        UniquePathsIII980 uniquePathsIII980 = new UniquePathsIII980();
        System.out.println(uniquePathsIII980.uniquePathsIII(new int[][] {{0, 1}, {2, 0}}));

    }
}
