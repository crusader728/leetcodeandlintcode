package withJava.crusader728.leetcode.monotonic;

import java.util.*;

public class SlidingWindowMaximum239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < nums.length; ++i) {
            while(!queue.isEmpty() && (i - queue.peek() + 1) > k) {
                queue.poll();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            if(i - k + 1 >= 0) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum239 slidingWindowMaximum239 = new SlidingWindowMaximum239();
        slidingWindowMaximum239.maxSlidingWindow(new int[] {1,3,1,2,0,5}, 3);
    }


}
