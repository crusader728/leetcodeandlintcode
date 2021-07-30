package java.crusader728.lintcode;

public class ID1687_PaveSquare {
    public long getTotalSchemes(int n) {
        int f1 = 1, f2 = 1, ans = 2;
        if (n < 2) return 1;
        for (int i = 2; i <= n; i++) {
            ans = f1 + f2;
            f1 = f2;
            f2 = ans;
        }
        return ans;
    }
}
