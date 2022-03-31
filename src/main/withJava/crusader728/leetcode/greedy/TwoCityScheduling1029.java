package withJava.crusader728.leetcode.greedy;

import java.util.*;

public class TwoCityScheduling1029 {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[0] - a[1], b[0] - b[1]);
            }
        });

        int ans = 0;
        int n = costs.length / 2;
        for(int i = 0; i < costs.length; ++i) {
            if(i < n) {
                ans += costs[i][0];
            } else {
                ans += costs[i][1];
            }
        }
        return ans;
    }
}
