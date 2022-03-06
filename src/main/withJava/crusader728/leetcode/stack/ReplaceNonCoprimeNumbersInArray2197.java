package withJava.crusader728.leetcode.stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class ReplaceNonCoprimeNumbersInArray2197 {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int n: nums) {
            stack.push(n);
            while(stack.size() >= 2) {
                int second = stack.pop();
                int first = stack.pop();
                int gcd = gcd(Math.max(first, second), Math.min(first, second));
                if(gcd == 1) {
                    stack.push(first);
                    stack.push(second);
                    break;
                } else {
                    int lcm = lcm(first, second, gcd);
                    stack.push(lcm);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> descendingIterator = stack.descendingIterator();
        while(descendingIterator.hasNext()) {
            result.add(descendingIterator.next());
        }
        return result;
    }

    private int lcm(int first, int second, int gcd) {
        if(first == second) {
            return first;
        } else {
            return Long.valueOf((long)first * second / gcd).intValue();
        }
    }

    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        ReplaceNonCoprimeNumbersInArray2197 replaceNonCoprimeNumbersInArray2197 = new ReplaceNonCoprimeNumbersInArray2197();
        replaceNonCoprimeNumbersInArray2197.replaceNonCoprimes(new int[] {31,97561,97561,97561,97561,97561,97561,97561,97561});
    }
}
