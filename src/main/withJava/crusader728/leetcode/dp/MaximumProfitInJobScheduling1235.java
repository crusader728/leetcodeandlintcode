package withJava.crusader728.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProfitInJobScheduling1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Info[] infos = new Info[startTime.length];
        for(int i = 0; i < startTime.length; ++i){
            infos[i] = new Info(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(infos, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.start - o2.start;
            }
        });

        int[] dp = new int[infos.length + 1];
        for(int i = dp.length - 1; i >= 0; --i) {
            if(i == dp.length - 1) {
                dp[i] = 0;
            } else {
                int next = Math.min(infos.length, upperBound(infos, infos[i].end));
                dp[i] = Math.max(dp[i + 1], infos[i].profit + dp[next]);
            }
        }
        return dp[0];

    }

    private int upperBound(Info[] infos, int target) {
        int l = 0;
        int r = infos.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(infos[mid].start < target) {
                l = mid + 1;
            } else if(infos[mid].start >= target) {
                r = mid;
            }
        }
        return l;
    }

    private static class Info {
        int start;
        int end;
        int profit;

        Info(int s, int e, int profit) {
            this.start = s;
            this.end = e;
            this.profit = profit;
        }
    }
}
