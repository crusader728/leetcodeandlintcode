package withJava.crusader728.leetcode.sweepline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRoomII253 {
    public int minMeetingRooms(int[][] intervals) {
        List<TimeStamp> ts = new ArrayList<>();
        for(int[] interval: intervals) {
            Start start = new Start();
            start.val = interval[0];
            End end = new End();
            end.val = interval[1];
            ts.add(start);
            ts.add(end);
        }

        Collections.sort(ts, (o1, o2) -> {
            int result = Integer.compare(o1.val, o2.val);
            if(result != 0) {
                return result;
            } else {
                if(o1 instanceof Start && o2 instanceof End) {
                    return 1;
                } else if(o1 instanceof End && o2 instanceof Start) {
                    return -1;
                } else {
                    return -1;
                }
            }
        });

        int count = 0;
        int maxCount = 0;
        for (TimeStamp t : ts) {
            if (t instanceof Start) {
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                count--;
            }
        }
        return maxCount;
    }

    private static abstract class TimeStamp {
        int val;
    }

    private static class Start extends TimeStamp {

    }

    private static class End extends TimeStamp {

    }

}
