package withJava.crusader728.lintcode;

public class ID1347_FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        if(n >= 5) {
            return n / 5 + trailingZeroes(n / 5);
        } else {
            return 0;
        }
    }
}
