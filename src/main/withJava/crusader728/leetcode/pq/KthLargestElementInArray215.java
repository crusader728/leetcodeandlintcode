package withJava.crusader728.leetcode.pq;

import java.util.PriorityQueue;

public class KthLargestElementInArray215 {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || k > nums.length || k < 1) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int n: nums) {
            priorityQueue.offer(n);
            while(priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.poll();
    }
}
