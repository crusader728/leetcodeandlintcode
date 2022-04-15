package withJava.crusader728.leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LargestNumberAfterDigitSwapsByParity2231 {
    public int largestInteger(int num) {
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        int n = num;
        while(n != 0) {
            int d = n % 10;
            if(d % 2 == 0) {
                evens.add(d);
            } else {
                odds.add(d);
            }
            n = n / 10;
        }

        Collections.sort(evens);
        Collections.sort(odds);
        int i = 0;
        int j = 0;
        n = num;
        List<Integer> result = new ArrayList<>();
        while(n != 0) {
            int d = n % 10;
            if(d % 2 == 0) {
                result.add(evens.get(i++));
            } else {
                result.add(odds.get(j++));
            }
            n = n / 10;
        }
        int ans = 0;
        for(int k = result.size() - 1; k >= 0; k--) {
            ans = ans * 10 + result.get(k);
        }
        return ans;
    }

    public static void main(String[] args) {
        LargestNumberAfterDigitSwapsByParity2231 largestNumberAfterDigitSwapsByParity2231 = new LargestNumberAfterDigitSwapsByParity2231();
        largestNumberAfterDigitSwapsByParity2231.largestInteger(1234);
    }
}
