package withJava.crusader728.leetcode.binarysearch;

public class Sqrt69 {
    public int mySqrt(int x) {
        if(x == 0 || x == 1) {
            return x;
        }

        int l = 1;
        int r = x;
        while(l < r) {
            int mid = l + (r - l) / 2;
            long probe = (long)mid * (long)mid;
            if(probe < x) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        if(l * l == x) {
            return l;
        } else {
            return l - 1;
        }
    }
}
