package java.crusader728.leetcode.parser;

public class StringToInteger8 {
    public int myAtoi(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }
        return runParser(s);
    }

    private int runParser(String s) {
        int i = 0;
        while(i < s.length()) {
            if(!(s.charAt(i) == ' ' || s.charAt(i) == '\t')) {
                break;
            } else {
                i++;
            }
        }
        if(i >= s.length()) {
            return 0;
        }
        char ch = s.charAt(i);
        if(ch != '+' && ch != '-' && !isDigit(ch)) {
            return 0;
        } else if(ch == '+') {
            return positive(s, i + 1);
        } else if(ch == '-') {
            return negative(s, i + 1);
        } else {
            return number(s, i, true);
        }
    }

    private int number(String s, int start, boolean positive) {
        int result = 0;
        int i = start;
        int max = positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        while(i < s.length()) {
            char ch = s.charAt(i);
            if(isDigit(ch)) {
                int v = ch - '0';
                if(!positive) {
                    v = -1 * v;
                }
                if(wontOverflow(result, v, max, positive)) {
                    result = result * 10 + v;
                } else {
                    result = positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    break;
                }
            } else {
                break;
            }
            i++;
        }
        return result;
    }

    private int positive(String s, int i) {
        if(i >= s.length()) {
            return 0;
        }
        if(isDigit(s.charAt(i))) {
            return number(s, i, true);
        } else {
            return 0;
        }
    }

    private int negative(String s, int i) {
        if(i >= s.length()) {
            return 0;
        }
        if(isDigit(s.charAt(i))) {
            return number(s, i, false);
        } else {
            return 0;
        }
    }

    private boolean wontOverflow(int current, int digit, int target, boolean positive) {
        if(positive) {
            return current < target / 10 || (current == target / 10 && digit <= target % 10);
        } else {
            return current > target / 10 || (current == target / 10 && digit >= target % 10);
        }
    }

    private boolean isDigit(char ch) {
        return 0 <= ch - '0' && ch - '0' <= 9;
    }
}
