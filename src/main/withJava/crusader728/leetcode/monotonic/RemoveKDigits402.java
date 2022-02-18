package withJava.crusader728.leetcode.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits402 {
    public String removeKDigits(String num, int k) {
        if(num == null || num.length() == 0) {
            throw new IllegalArgumentException();
        }
        if(k == 0) {
            return num;
        }
        if(k >= num.length()) {
            return "0";
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        while(i < num.length()) {
            int n = num.charAt(i) - '0';
            while(!stack.isEmpty() && n < stack.peekFirst() && k > 0) {
                stack.pop();
                k--;
            }
            stack.addFirst(n);
            i++;
        }

        while(k > 0 && !stack.isEmpty()) {
            stack.removeFirst();
            k--;
        }

        StringBuilder builder = new StringBuilder();
        while(!stack.isEmpty()) {
            builder.append((char)('0' + stack.pop()));
        }
        i = builder.length() - 1;
        while(i > 0 && builder.charAt(i) == '0') {
            i--;
        }
        StringBuilder result = new StringBuilder();
        while(i >= 0) {
            result.append(builder.charAt(i));
            i--;
        }
        return result.toString();
    }
}
