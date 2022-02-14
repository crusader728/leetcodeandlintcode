package withJava.crusader728.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram84 {
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) {
            throw new IllegalArgumentException();
        }

        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Deque<Integer> monotonic = new ArrayDeque<>();
        //populate right array
        for(int i = 0; i < heights.length; ++i) {
            if(monotonic.isEmpty()) {
                monotonic.push(i);
            } else {
                if(heights[i] >= heights[monotonic.peek()]) {
                    monotonic.push(i);
                } else {
                    while(!monotonic.isEmpty() && heights[monotonic.peek()] > heights[i]) {
                        int idx = monotonic.pop();
                        right[idx] = i - 1;
                    }
                    monotonic.push(i);
                }
            }
        }
        while(!monotonic.isEmpty()) {
            right[monotonic.pop()] = heights.length - 1;
        }

        //populate left array
        for(int i = heights.length - 1; i >= 0; --i) {
            if(monotonic.isEmpty()) {
                monotonic.push(i);
            } else {
                if(heights[i] >= heights[monotonic.peek()]) {
                    monotonic.push(i);
                } else {
                    while(!monotonic.isEmpty() && heights[monotonic.peek()] > heights[i]) {
                        int idx = monotonic.pop();
                        left[idx] = i + 1;
                    }
                    monotonic.push(i);
                }
            }
        }
        while(!monotonic.isEmpty()) {
            left[monotonic.pop()] = 0;
        }


        int max = 0;
        for(int i = 0; i < heights.length; ++i) {
            if(heights[i] == 0) {
                continue;
            }
            max = Math.max(max, heights[i] * (right[i] - left[i] + 1));
        }

        return max;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram84 largestRectangleInHistogram84 = new LargestRectangleInHistogram84();
        System.out.println(largestRectangleInHistogram84.largestRectangleArea(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
