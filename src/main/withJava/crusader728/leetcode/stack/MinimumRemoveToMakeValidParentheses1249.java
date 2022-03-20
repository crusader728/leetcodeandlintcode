package withJava.crusader728.leetcode.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses1249 {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> toRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == '(') {
                stack.add(i);
            } else if(s.charAt(i) == ')') {
                if(stack.isEmpty()) {
                    toRemove.add(i);
                } else {
                    stack.pop();
                }
            } 
        }
        toRemove.addAll(stack);
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length(); ++i) {
            if(!toRemove.contains(i)) {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
