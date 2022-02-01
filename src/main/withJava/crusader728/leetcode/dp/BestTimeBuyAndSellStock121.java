package withJava.crusader728.leetcode.dp;

class BestTimeBuyAndSellStock121 {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        } 

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; ++i) {
            if(minPrice > prices[i]) {
                minPrice = prices[i];
            } 
            if(prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}