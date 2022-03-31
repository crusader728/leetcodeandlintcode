package withJava.crusader728.leetcode.dp;

import java.util.*;

public class PaintHouse256 {
    public int minCost(int[][] costs) {
        Map<String, Integer> memo = new HashMap<>();
        return 
            Math.min(dfs(costs, 0, 0, memo), 
                Math.min(dfs(costs, 0, 1, memo), dfs(costs, 0, 2, memo)));
    }

    private int dfs(int[][] costs, int i, int color, Map<String, Integer> memo) {
        String key = String.format("%d_%d", i, color);
        if(memo.containsKey(key)) {
            return memo.get(key);
        }

        if(i == costs.length - 1) {
            memo.put(key, costs[i][color]);
            return costs[i][color];
        } 

        int min = Integer.MAX_VALUE;
        for(int c = 0; c < 3; ++c) {
            if(c != color) {
                min = Math.min(min, costs[i][color] + dfs(costs, i + 1, c, memo));
            }
        }
        memo.put(key, min);
        return min;
    }
}
