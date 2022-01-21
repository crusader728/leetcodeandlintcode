package withJava.crusader728.leetcode.stack;

import java.util.Stack;

public class ValidateParentheses20 {
    public boolean isValid(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(isOpeningParenthesis(ch)) {
                stack.push(ch);
            } else if(isClosingParenthesis(ch)) {
                if(stack.isEmpty()) {
                    return false;
                } else if(stack.peek() == matchingOpeningParenthesis(ch)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private boolean isOpeningParenthesis(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    private boolean isClosingParenthesis(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    private char matchingOpeningParenthesis(char ch) {
        switch (ch) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                throw new IllegalStateException();
        }
    }
}
