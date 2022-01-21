package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.*;

public class GenerateParentheses22 {
    public List<String> generateParenthesis(int n) {
        Set<String> result = new HashSet<>();
        helper(n, n, new Stack<>(), new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void helper(int quotaOpen, int quotaClose, Stack<Character> stack, StringBuilder stringBuilder, Set<String> result) {
        if(quotaOpen == 0 && quotaClose == 0 && stack.isEmpty()) {
            result.add(stringBuilder.toString());
        }

        if(quotaOpen > 0) {
            stringBuilder.append('(');
            stack.push('(');
            helper(quotaOpen - 1, quotaClose, stack, stringBuilder, result);
            stack.pop();
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if(quotaClose > 0 && !stack.isEmpty()) {
            stringBuilder.append(')');
            stack.pop();
            helper(quotaOpen, quotaClose - 1, stack, stringBuilder, result);
            stack.push('(');
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses22 generateParentheses22 = new GenerateParentheses22();
        List<String> strings = generateParentheses22.generateParenthesis(3);
        strings.forEach(System.out::println);
    }
}
