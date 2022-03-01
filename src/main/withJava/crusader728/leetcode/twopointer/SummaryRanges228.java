package withJava.crusader728.leetcode.twopointer;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges228 {
    public List<String> summaryRanges(int[] nums) {
        if(nums == null || nums.length == 0) {
            return new ArrayList<>();
        } 

        int l = 0;
        int r = 1;
        List<String> result = new ArrayList<>();
        while(r < nums.length) {
            if(nums[r - 1] != nums[r] - 1) {
                if(l == r - 1) {
                    result.add(String.valueOf(nums[l]));
                } else {
                    result.add(String.format("%d->%d", nums[l], nums[r - 1]));
                }
                l = r;
            }
            r++;
        }
        if(l == nums.length - 1) {
            result.add(String.valueOf(nums[l]));
        } else {
            result.add(String.format("%d->%d", nums[l], nums[nums.length - 1]));
        }
        return result;
    }
}
