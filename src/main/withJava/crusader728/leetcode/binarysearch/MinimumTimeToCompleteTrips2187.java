package withJava.crusader728.leetcode.binarysearch;

import java.util.Arrays;

public class MinimumTimeToCompleteTrips2187 {
    public long minimumTime(int[] time, int totalTrips) {
        long l = 0;
        long r = 100000000000000L;
        while(l < r) {
            long mid = l + (r - l) / 2;
            long trips = getTrips(time, mid);
            if(trips < totalTrips) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private long getTrips(int[] time, long mid) {
        long sum = 0;
        for (int value : time) {
            sum += mid / value;
        }
        return sum;
    }

    public static void main(String[] args) {
        MinimumTimeToCompleteTrips2187 minimumTimeToCompleteTrips2187 = new MinimumTimeToCompleteTrips2187();
        minimumTimeToCompleteTrips2187.minimumTime(new int[] {1,2,3}, 5);
    }
}
