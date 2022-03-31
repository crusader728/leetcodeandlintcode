package withJava.crusader728.leetcode.dfs;

import java.util.*;

public class NestedListWeightSumII364 {
    private interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int[] sum = new int[1];
        int[] weightedSum = new int[1];
        int maxDepth = Integer.MIN_VALUE;
        for(NestedInteger nestedInteger: nestedList) {
            maxDepth = Math.max(maxDepth, dfs(nestedInteger, 1, weightedSum, sum));
        }
        return (maxDepth + 1) * sum[0] - weightedSum[0];
    }

    private int dfs(NestedInteger nestedInteger, int i, int[] weightedSum, int[] sum) {
        if(nestedInteger.isInteger()) {
            weightedSum[0] += i * nestedInteger.getInteger();
            sum[0] += nestedInteger.getInteger();
            return i;
        } else {
            int maxDepth = Integer.MIN_VALUE;
            for(NestedInteger ni: nestedInteger.getList()) {
                maxDepth = Math.max(maxDepth, dfs(ni, i + 1, weightedSum, sum));
            }
            return maxDepth;
        }
    }

}
