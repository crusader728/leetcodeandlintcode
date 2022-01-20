package java.crusader728.leetcode.binarysearch;

import java.util.Arrays;
import java.util.stream.Stream;

public class KokoEatingBananas875 {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for(int pile : piles) {
            right = Math.max(pile, right);
        }
        while(left < right) {
            int middle = left + (right - left) / 2;
            int hours = hoursToFinish(piles, middle);
            if(hours > h) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return right;
    }

    private int hoursToFinish(int[] piles, int speed) {
        int hours = 0;
        for(int pile: piles) {
            int q = pile / speed;
            int r = pile % speed;
            if(r == 0) {
                hours += q;
            } else {
                hours += q + 1;
            }
        }
        return hours;
    }
}
