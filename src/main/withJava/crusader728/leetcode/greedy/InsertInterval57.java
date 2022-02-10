package withJava.crusader728.leetcode.greedy;

import java.util.*;

public class InsertInterval57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[] merged = new int[] {newInterval[0], newInterval[1]};
        int removed = 0;
        for(int i = 0; i < intervals.length; ++i) {
            if(!(intervals[i][1] < merged[0] || intervals[i][0] > merged[1])) {
                merged[0] = Math.min(merged[0], intervals[i][0]);
                merged[1] = Math.max(merged[1], intervals[i][1]);
                removed++;
            }
        }
        int[][] result = new int[intervals.length + 1 - removed][2];
        int count = 0;
        for(int i = 0; i < intervals.length; ++i) {
            if(intervals[i][1] < merged[0]) {
                result[count][0] = intervals[i][0];
                result[count][1] = intervals[i][1];            
                count++;
            } else {
                break;
            }
        }
        result[count][0] = merged[0];
        result[count][1] = merged[1];
        count++;
        for(int i = 0; i < intervals.length; ++i) {
            if(intervals[i][0] > merged[1]) {
                result[count][0] = intervals[i][0];
                result[count][1] = intervals[i][1];            
                count++;
            }
        }
        
        return result;
    }
}
