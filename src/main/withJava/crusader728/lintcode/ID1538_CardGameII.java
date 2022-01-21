package withJava.crusader728.lintcode;

public class ID1538_CardGameII {
    public boolean cardGame(int[] cost, int[] damage, int totalMoney, int totalDamage) {
        if(cost == null || damage == null || totalMoney == 0 || cost.length == 0 || damage.length == 0) {
            return totalDamage == 0;
        } else {
            return maximumDamage(cost, damage, totalMoney) >= totalDamage;
        }
    }

    private int maximumDamage(int[] cost, int[] damage, int totalMoney) {
        int[][] memo = new int[cost.length + 1][totalMoney + 1];
        for(int i = cost.length; i >= 0; --i) {
            for(int j = totalMoney; j >= 0; --j) {
                memo[i][j] = -1;
            }
        }
        return helper(cost, damage, totalMoney, 0, memo);
    }

    private int helper(int[] cost, int[] damage, int m, int i, int[][] memo) {
        if(memo[i][m] != -1) {
            return memo[i][m];
        }
        int result;
        if(i == cost.length) {
            result = 0;
        } else {
            if (m < cost[i]) {
                result = helper(cost, damage, m, i + 1, memo);
            } else {
                result = Math.max(
                        helper(cost, damage, m, i + 1, memo),
                        helper(cost, damage, m - cost[i], i + 1, memo) + damage[i]
                );
            }
        }
        memo[i][m] = result;
        return result;
    }
}
