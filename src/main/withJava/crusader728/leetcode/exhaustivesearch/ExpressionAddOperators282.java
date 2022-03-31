package withJava.crusader728.leetcode.exhaustivesearch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ExpressionAddOperators282 {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        Stack<Long> stack = new Stack<>();
        List<String> component = new ArrayList<>();
        search(num, target, 0, stack, component, result);
        return result;
    }

    private void search(String num, int target, int i, Stack<Long> stack, List<String> component, List<String> result) {
        if(i == num.length()) {
            Long s = 0L;
            Iterator<Long> iterator = stack.iterator();
            while(iterator.hasNext()) {
                s += iterator.next();
            }
            if(s.intValue() == target) {
                result.add(String.join("", component));
            } 
            return;
        }
        for(int l = 1; i + l <= num.length(); ++l) {
            String sub = num.substring(i, i + l);
            Long n = Long.parseLong(sub);
            if(i == 0) {
                stack.push(n);
                component.add(sub);
                search(num, target, i + l, stack, component, result);
                stack.pop();
                component.remove(component.size() - 1);
            } else {
                for(String op: ops) {
                    component.add(op);
                    component.add(sub);
                    if("+".equals(op)) {
                        stack.push(n);
                        search(num, target, i + l, stack, component, result);
                        stack.pop();
                    } else if("-".equals(op)) {
                        stack.push(-1 * n);
                        search(num, target, i + l, stack, component, result);
                        stack.pop();
                    } else if(!n.equals(0L)) {
                        Long pop = stack.pop();
                        stack.push(pop * n);
                        search(num, target, i + l, stack, component, result);
                        Long v = stack.pop();
                        stack.push(v / n);
                    }
                    component.remove(component.size() - 1);
                    component.remove(component.size() - 1);
                }
            }
        }
    }

    private static String[] ops = new String[] {"+", "-", "*"};
}
