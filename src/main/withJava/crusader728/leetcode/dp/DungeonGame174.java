package withJava.crusader728.leetcode.dp;

public class DungeonGame174 {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0) {
            return 0;
        }

        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m][n];
        for(int r = m - 1; r >= 0; r--) {
            for(int c = n - 1; c >= 0; c--) {
                if(r == m - 1 && c == n - 1) {
                    dp[r][c] = Math.max(1 - dungeon[r][c], 1);
                } else {
                    int answer = Integer.MAX_VALUE;
                    for(int i = 0; i < dx.length; ++i) {
                        int x = r + dx[i];
                        int y = c + dy[i];
                        if(x >= 0 && x < dungeon.length && y >= 0 && y < dungeon[x].length) {
                            answer = Math.min(answer, Math.max(1, dp[x][y] - dungeon[r][c]));
                        }
                    }
                    dp[r][c] = answer;
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonGame174 dungeonGame174 = new DungeonGame174();
        int i = dungeonGame174.calculateMinimumHP(new int[][]{{0, 0}});
        System.out.println(i);
    }

    private int[] dx = new int[] {0, 1};
    private int[] dy = new int[] {1, 0};
}
