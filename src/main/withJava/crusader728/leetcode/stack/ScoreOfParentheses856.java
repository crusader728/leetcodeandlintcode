package withJava.crusader728.leetcode.stack;

import java.util.Stack;

public class ScoreOfParentheses856 {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int currentScore = 0;
        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == '(') {
                stack.push(currentScore);
                currentScore = 0;
            } else {
                int prev = stack.pop();
                currentScore = Math.max(2 * currentScore, 1) + prev;
            }
        }
        return currentScore;
    }
}
