package withJava.crusader728.leetcode.pq;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> intFreq = new HashMap<>();
        for(int n: nums) {
            intFreq.put(n, intFreq.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return Integer.compare(o1.freq , o2.freq);
            }
        });

        for(Map.Entry<Integer, Integer> entry: intFreq.entrySet()) {
            if(pq.size() < k) {
                pq.offer(new Info(entry.getKey(), entry.getValue()));
            } else if(pq.peek().freq < entry.getValue()) {
                pq.offer(new Info(entry.getKey(), entry.getValue()));
                pq.poll();
            }
        }

        int[] result = new int[k];
        int i = 0;
        while(!pq.isEmpty()) {
            result[i++] = pq.poll().num;
        }
        return result;
    }

    private static class Info {
        int num;
        int freq;

        Info(int n, int f) {
            this.num = n;
            this.freq = f;
        }
    }
}
