package withJava.crusader728.leetcode.pq;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin973 {
    public int[][] kClosest(int[][] points, int k) {
        Comparator<int[]> distComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int d1 = o1[0] * o1[0] + o1[1] * o1[1];
                int d2 = o2[0] * o2[0] + o2[1] * o2[1];
                return d1 - d2;
            }            
        };   
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(distComparator.reversed());
        for(int i = 0; i < points.length; ++i) {
            if(pq.size() < k) {
                pq.add(points[i]);
            } else if(distComparator.compare(points[i], pq.peek()) < 0) {
                pq.add(points[i]);
                pq.poll();
            }
        }
        int[][] result = new int[k][2];
        for(int i = k - 1; i >= 0; --i) {
            int[] poll = pq.poll();
            result[i][0] = poll[0];
            result[i][1] = poll[1];
        }
        return result;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin973 kClosestPointsToOrigin973 = new KClosestPointsToOrigin973();
        kClosestPointsToOrigin973.kClosest(new int[][] {
                {-1, 3},
                {2, 2}
        }, 1);
    }
}
