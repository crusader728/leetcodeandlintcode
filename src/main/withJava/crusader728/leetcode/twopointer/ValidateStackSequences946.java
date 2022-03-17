package withJava.crusader728.leetcode.twopointer;

import java.util.Stack;

public class ValidateStackSequences946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int j = 0;
        while(i < pushed.length) {
            stack.push(pushed[i]);
            while(!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
            i++;
        }
        return j == popped.length;
    }
}
