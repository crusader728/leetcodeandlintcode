package withJava.crusader728.leetcode.pq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII253 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        for(int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];
            if(pq.isEmpty()) {
                pq.offer(end);
            } else {
                while(!pq.isEmpty() && pq.peek() <= start) {
                    pq.poll();
                }
                pq.offer(end);
            }
            count = Math.max(count, pq.size());
        }
        return count;
    }
}
