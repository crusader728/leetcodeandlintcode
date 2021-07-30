package java.crusader728.lintcode;

public class ID1684_KWolf {
    /**
     * @param l: the start range
     * @param r: the end range
     * @param k: the number of digits that should be different
     * @return: the number of K-wolf numbers in range [l,r]
     */
    int INF = 0x3f3f3f3f;
    double eps = 1e-8;

    long bit[] = new long[19];
    long dp[][] = new long[19][20100];
    int mod;

    boolean cal(int c, int x) {
        while (c / 10 > 0) {
            if (c % 10 == x) return false;
            c /= 10;
        }
        return true;
    }

    long dfs(boolean h, int pos, int hs, int pre) {
        if (pos == -1) return 1;
        while (pre - mod / 10 >= mod / 10) pre -= mod / 10;
        if (!h && dp[pos][pre] != -1) return dp[pos][pre];

        long end = h ? bit[pos] : 9;
        int start;
        long answer = 0;

        for (start = 0; start <= end; ++start) {
            if (!cal(pre, start)) continue;
            if (hs == 0 && (start == 0)) answer += dfs(h && (start == end), pos - 1, hs, pre);
            else answer += dfs(h && (start == end), pos - 1, hs + 1, pre * 10 + start);
        }

        if (!h) dp[pos][pre] = answer;
        return answer;
    }

    long solve(long x) {
        if (x == 0) return 1;
        int len = 0;
        while (x > 0) {
            bit[len++] = x % 10;
            x /= 10;
        }
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 20100; j++) {
                dp[i][j] = -1;
            }
        }
        return dfs(true, len - 1, 0, 1);
    }

    public long getCount(long l, long r, int k) {

        mod = 1;
        for (int i = 0; i < k; ++i) {
            mod *= 10;
        }
        return solve(r) - solve(l - 1);
    }
}

