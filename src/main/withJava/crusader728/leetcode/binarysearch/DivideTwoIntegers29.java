package withJava.crusader728.leetcode.binarysearch;

public class DivideTwoIntegers29 {
    public int divide(int dividend, int divisor) {
        if(divisor == 0) {
            throw new IllegalArgumentException();
        }

        if(dividend == 0) {
            return 0;
        }

        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if(divisor == 1) {
            return dividend;
        }

        int negatives = 2;
        int p;
        if(dividend > 0) {
            negatives--;
            p = -dividend;
        } else {
            p = dividend;
        }
        int q;
        if(divisor > 0) {
            negatives--;
            q = -divisor;
        } else {
            q = divisor;
        }
        int r = 0;
        while(p <= q) {
            int value = q;
            int count = 1;
            while(value + value < 0 && value + value > p) {
                value = value + value;
                count = count + count;
            }
            p = p + value;
            r += count;
        }
        if(negatives == 1) {
            return -r;
        } else {
            return r;
        }
    }
}
