package withJava.crusader728.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GreyCode89 {
    public List<Integer> grayCode(int n) {
        if(n == 1) {
            return Arrays.asList(0, 1);
        }
        else {
            List<Integer> result = new ArrayList<>();
            List<Integer> prev = grayCode(n - 1);
            for(int i = 0; i < prev.size(); ++i) {
                result.add(prev.get(i) * 2 + 0);
            }
            for(int i = prev.size() - 1; i >= 0; --i) {
                result.add(prev.get(i) * 2 + 1);
            }
            return result;
        }
    }
}
