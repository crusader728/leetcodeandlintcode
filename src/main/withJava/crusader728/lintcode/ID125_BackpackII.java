package withJava.crusader728.lintcode;

public class ID125_BackpackII {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        int[][] memo = new int[A.length + 1][m + 1];
        for(int i = 0; i < A.length + 1; ++i) {
            for(int j = 0; j <= m; ++j) {
                memo[i][j] = -1;
            }
        }
        return helper(A, V, m, 0, memo);
    }

    private int helper(int[] A, int[] V, int m, int i, int[][] memo) {
        if(memo[i][m] != -1) {
            return memo[i][m];
        } else {
            int result;
            if(i == A.length) {
                result = 0;
            } else {
                if(m >= A[i]) {
                    result = Math.max(helper(A, V, m, i + 1, memo), helper(A, V, m - A[i], i + 1, memo) + V[i]);
                } else {
                    result = helper(A, V, m, i + 1, memo);
                }
            }
            memo[i][m] = result;
            return result;
        }
    }
}
