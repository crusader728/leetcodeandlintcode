package withJava.crusader728.leetcode.sweepline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals56 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int currentStart = -1;
        int currentEnd = -1;
        List<int[]> result = new ArrayList<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (currentStart == -1) {
                currentStart = start;
                currentEnd = end;
            }
            if (start <= currentEnd) {
                currentEnd = Math.max(end, currentEnd);
            } else {
                result.add(new int[]{currentStart, currentEnd});
                currentStart = start;
                currentEnd = end;
            }
        }
        result.add(new int[] {currentStart, currentEnd});
        return result.toArray(new int[result.size()][]);
    }
}
