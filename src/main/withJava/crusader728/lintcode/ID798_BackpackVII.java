package withJava.crusader728.lintcode;

import java.util.function.BinaryOperator;

public class ID798_BackpackVII {
    class Bag {
        int type;

        Bag(int type) {
            this.type = type;
        }
    }
    public int backPackVII(int n, int[] prices, int[] weight, int[] amounts) {
        int totalAmount = 0;
        for(int i = 0; i < amounts.length; ++i) {
            totalAmount += amounts[i];
        }
        Bag[] bags = new Bag[totalAmount];
        int count = 0;
        for(int i = 0; i < amounts.length; ++i) {
            for (int j = 0; j < amounts[i]; ++j) {
                bags[count] = new Bag(i);
                count++;
            }
        }

        int[][] memo = new int[totalAmount + 1][n + 1];
        for(int i = totalAmount; i >= 0; --i) {
            for(int j = n; j >= 0; --j) {
                memo[i][j] = -1;
            }
        }

        return helper(prices, weight, bags, n, 0, memo);
    }

    private int helper(int[] prices, int[] weight, Bag[] bags, int n, int i, int[][] memo) {
        if(memo[i][n] != -1) {
            return memo[i][n];
        } else {
            int result;
            if(i == bags.length) {
                result = 0;
            } else {
                Bag bag = bags[i];
                int price = prices[bag.type];
                if(price > n) {
                    result = helper(prices, weight, bags, n, i + 1, memo);
                } else {
                    result = Math.max(
                            helper(prices, weight, bags, n, i + 1, memo),
                            helper(prices, weight, bags, n - price, i + 1, memo) + weight[bag.type]
                    );
                }
            }
            memo[i][n] = result;
            return result;
        }
    }
}
