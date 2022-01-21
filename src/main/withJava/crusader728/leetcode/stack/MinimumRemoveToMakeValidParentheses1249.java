package withJava.crusader728.leetcode.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveToMakeValidParentheses1249 {
    public String minRemoveToMakeValid(String s) {
        Set<Integer> toRemove = new HashSet<>();
        Stack<Integer> lefts = new Stack<>();
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(ch == '(') {
                lefts.push(i);
            } else if(ch == ')') {
                if(lefts.isEmpty()) {
                    toRemove.add(i);
                } else {
                    lefts.pop();
                }
            }
        }
        while(!lefts.isEmpty()) {
            toRemove.add(lefts.pop());
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < s.length(); ++i) {
            if(!toRemove.contains(i)) {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
