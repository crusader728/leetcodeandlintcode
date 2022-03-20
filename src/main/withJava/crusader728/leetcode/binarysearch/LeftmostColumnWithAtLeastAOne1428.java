package withJava.crusader728.leetcode.binarysearch;

import java.util.List;

public class LeftmostColumnWithAtLeastAOne1428 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int r = dimensions.get(0);
        int c = dimensions.get(1);

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < r; ++i) {
            min = Math.min(min, binarySearch(binaryMatrix, c, i));
        }
        if(min == c) {
            return -1;
        } else {
            return min;
        }
    }

    private int binarySearch(BinaryMatrix binaryMatrix, int c, int i) {
        int l = 0;
        int r = c;
        while(l < r) {
            int mid = l + (r - l) / 2;
            int v = binaryMatrix.get(i, mid);
            if(v == 1) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static interface BinaryMatrix {
        int get(int r, int c);
        List<Integer> dimensions();
    }
}
