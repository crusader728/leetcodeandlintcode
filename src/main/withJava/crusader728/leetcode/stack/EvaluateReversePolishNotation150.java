package withJava.crusader728.leetcode.stack;

import java.util.*;

public class EvaluateReversePolishNotation150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; ++i) {
            Integer lhs;
            Integer rhs;
            switch(tokens[i]) {
                case "+":
                    rhs = stack.pop();
                    lhs = stack.pop();
                    stack.push(lhs + rhs);
                    break;
                case "-":
                    rhs = stack.pop();
                    lhs = stack.pop();
                    stack.push(lhs - rhs);
                    break;
                case "*":
                    rhs = stack.pop();
                    lhs = stack.pop();
                    stack.push(lhs * rhs);
                    break;
                case "/":
                    rhs = stack.pop();
                    lhs = stack.pop();
                    stack.push(lhs / rhs);
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
                    break;
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }
}
