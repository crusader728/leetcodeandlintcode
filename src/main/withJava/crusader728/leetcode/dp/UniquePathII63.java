package withJava.crusader728.leetcode.dp;

public class UniquePathII63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m][n];
        for(int i = m - 1; i >= 0; --i) {
            for(int j = n - 1; j >= 0; --j) {
                if(obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if(i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                } else if(i == m - 1) {
                    dp[i][j] = obstacleGrid[i][j + 1] == 1 ? 0 : dp[i][j + 1];
                } else if(j == n - 1) {
                    dp[i][j] = obstacleGrid[i + 1][j] == 1 ? 0 : dp[i + 1][j];
                } else {
                    dp[i][j] += obstacleGrid[i][j + 1] == 1 ? 0 : dp[i][j + 1];
                    dp[i][j] += obstacleGrid[i + 1][j] == 1 ? 0 : dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    } 
}
