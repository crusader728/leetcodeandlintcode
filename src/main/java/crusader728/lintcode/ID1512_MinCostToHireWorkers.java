package java.crusader728.lintcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class ID1512_MinCostToHireWorkers {
    private static class Worker {
        int id;
        Worker(int id) {
            this.id = id;
        }
    }
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        Worker[] workers = new Worker[quality.length];
        for(int i = 0; i < workers.length; ++i) {
            workers[i] = new Worker(i);
        }
        Arrays.sort(workers, (o1, o2) -> {
            double unitprice1 = (double)wage[o1.id] / (double)quality[o1.id];
            double unitprice2 = (double)wage[o2.id] / (double) quality[o2.id];
            if(unitprice1 == unitprice2) {
                return Integer.compare(quality[o1.id], quality[o2.id]);
            } else {
                return Double.compare(unitprice1, unitprice2);
            }
        });

        int sum = 0;
        double answer = Double.MAX_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < workers.length; ++i) {
            int q = quality[workers[i].id];
            int w = wage[workers[i].id];
            double unitPrice = (double) w / (double) q;
            if(pq.size() == k) {
                sum -= pq.poll();
            }
            pq.offer(q);
            sum += q;
            if(i >= k - 1) {
                answer = Math.min(answer, sum * unitPrice);
            }
        }
        return answer;
    }
}
