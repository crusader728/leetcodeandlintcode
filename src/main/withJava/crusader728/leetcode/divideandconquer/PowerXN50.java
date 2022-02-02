package withJava.crusader728.leetcode.divideandconquer;

public class PowerXN50 {
    public double myPow(double x, int n) {
        if(x == 0) {
            return 0;
        } else if(n == 0) {
            return 1;
        } else if(n < 0) {
            return internal(1.0 / x, -1 * n);
        } else {
            return internal(x, n);
        }
    }

    private double internal(double x, int n) {
        if(n == 0) {
            return 1;
        }
        if(n == 1) {
            return x;
        }
        double r = internal(x, n / 2);
        if(n % 2 == 0) {
            return r * r;
        } else {
            return r * r * x;
        }
    }


}
