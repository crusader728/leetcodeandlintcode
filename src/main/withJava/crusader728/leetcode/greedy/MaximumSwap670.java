package withJava.crusader728.leetcode.greedy;

import java.util.*;

public class MaximumSwap670 {
    public int maximumSwap(int num) {
        List<Integer> digits = getDigits(num);
        int[] rightMax = new int[digits.size()];
        populateRightMax(rightMax, digits); 
        for(int i = 0; i < digits.size(); ++i) {
            if(digits.get(i) < digits.get(rightMax[i])) {
                swap(digits, i, rightMax[i]);
                return toNumber(digits);
            }
        }
        return num;
    }

    private int toNumber(List<Integer> digits) {
        int n = 0;
        for(int d: digits) {
            n = n * 10 + d;
        }
        return n;
    }

    private void swap(List<Integer> digits, int i, int j) {
        int temp = digits.get(i);
        digits.set(i, digits.get(j));
        digits.set(j, temp);
    }

    private void populateRightMax(int[] rightMax, List<Integer> digits) {
        rightMax[digits.size() - 1] = digits.size() - 1;
        for(int i = digits.size() - 2; i >= 0; --i) {
            if(digits.get(rightMax[i + 1]) >= digits.get(i)) {
                rightMax[i] = rightMax[i + 1];
            } else {
                rightMax[i] = i;
            }
        }
    }

    private List<Integer> getDigits(int num) {
        int n = num;
        List<Integer> result = new ArrayList<>();
        while(n > 0) {
            result.add(0, n % 10);
            n = n / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumSwap670 maximumSwap670 = new MaximumSwap670();
        maximumSwap670.maximumSwap(98368);
    }
}
