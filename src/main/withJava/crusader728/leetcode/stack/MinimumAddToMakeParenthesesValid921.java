package withJava.crusader728.leetcode.stack;

import java.util.Stack;

public class MinimumAddToMakeParenthesesValid921 {
    public int minAddToMakeValid(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }

        int left = 0;
        int right = 0;
        for(int i = 0; i < s.length(); ++i) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                if(left > 0) {
                    left--;
                } else {
                    right++;
                }
            }
        }
        return left + right;
    }
}
