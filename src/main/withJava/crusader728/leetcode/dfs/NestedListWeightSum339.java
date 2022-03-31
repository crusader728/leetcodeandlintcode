package withJava.crusader728.leetcode.dfs;

import java.util.List;

public class NestedListWeightSum339 {
    public int depthSum(List<NestedInteger> nestedList) {
        int sum = 0;
        for(NestedInteger ni: nestedList) {
            sum += dfs(ni, 1, 0);
        }
        return sum;
    }

    private int dfs(NestedInteger ni, int depth, int acc) {
        if(ni.isInteger()) {
            return acc + ni.getInteger() * depth;
        } 
        List<NestedInteger> sub = ni.getList();
        int r = acc;
        for(NestedInteger x: sub) {
            r = dfs(x, depth + 1, r);
        }
        return r;
    }

    private interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }
}
