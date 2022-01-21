package withJava.crusader728.lintcode;

import java.util.HashMap;
import java.util.Map;

public class ID1682_MinimumPathSumIII {
    private int[] deltax = new int[] {1, 0, 1};
    private int[] deltay = new int[] {0, 1, 1};
    public int minimumPathSumIII(int[][] grid) {
        Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();

        return helper(grid, 0, 0, memo);
    }

    private int helper(int[][] grid, int x, int y, Map<Integer, Map<Integer, Integer>> memo) {
        if(memo.containsKey(x) && memo.get(x).containsKey(y)) {
            return memo.get(x).get(y);
        } else {
            int result = Integer.MAX_VALUE;
            if(x == grid.length - 1 && y == grid[x].length - 1) {
                result = Math.min(grid[x][y],result);
            } else {
                for(int i = 0; i < deltax.length; ++i) {
                    int newX = x + deltax[i];
                    int newY = y + deltay[i];
                    if(newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[newX].length) {
                        result = Math.min(grid[x][y] + helper(grid, newX, newY, memo), result);
                    }
                }
            }
            if(!memo.containsKey(x)) {
                memo.put(x, new HashMap<>());
            }
            memo.get(x).put(y, result);
            return result;
        }
    }
}
