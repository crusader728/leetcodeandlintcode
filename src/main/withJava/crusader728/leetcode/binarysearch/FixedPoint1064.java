package withJava.crusader728.leetcode.binarysearch;

public class FixedPoint1064 {
    public int fixedPoint(int[] arr) {
        int l = 0;
        int r = arr.length;
        while(l < r) {
            int mid = l + (r - l) / 2;
            int probe = arr[mid];
            if(probe >= mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if(l >= arr.length || arr[l] != l) {
            return -1;
        } else {
            return l;
        }
    }
}
