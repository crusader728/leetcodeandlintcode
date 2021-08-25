package java.crusader728.lintcode;

public class ID1906_PointsBiggerThanSurroundingArea {
    private int[] dx = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] dy = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};

    public int[][] highpoints(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] result = new int[n][m];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                boolean valid = true;
                for(int k = 0; k < dx.length; ++k) {
                    int newX = i + dx[k];
                    int newY = j + dy[k];
                    if(newX >= 0 && newX < n && newY >= 0 && newY < m) {
                        if(grid[newX][newY] >= grid[i][j]) {
                            valid = false;
                            break;
                        }
                    }
                }
                result[i][j] = valid ? 1 : 0;
            }
        }
        return result;
    }
}
