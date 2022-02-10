package withJava.crusader728.leetcode.parser;

public class BasicCalculatorIII772 {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        String replaced = s.replaceAll("\\s+", "");
        return expression(replaced, 0).result;

    }

    private Result number(String s, int idx) {
        int sum = 0;
        int i = idx;
        while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            sum = sum * 10;
            sum += s.charAt(i) - '0';
            i++;
        }
        return new Result(sum, i);
    }

    private Result element(String s, int idx) {
        boolean isPositive = true;
        int i = idx;
        if(s.charAt(idx) == '-') {
            isPositive = false;
            i = idx + 1;
        }

        if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            Result r = number(s, i);
            return new Result(isPositive ? r.result : -1 * r.result, r.pos);
        } else if(s.charAt(i) == '(') {
            Result r = expression(s, i + 1);
            if(r.pos >= s.length() || s.charAt(r.pos) != ')') {
                throw new RuntimeException();
            } else {
                return new Result(isPositive ? r.result : -1 * r.result, r.pos + 1);
            }
        } else {
            throw new RuntimeException();
        }
    }

    private Result expression(String s, int i) {
        return term(s, i);
    }

    private Result factor(String s, int idx) {
        Result left = element(s, idx);
        while(left.pos < s.length() && (s.charAt(left.pos) == '*' || s.charAt(left.pos) == '/')) {
            Result rhs = element(s, left.pos + 1);
            if(s.charAt(left.pos) == '*') {
                left = new Result(left.result * rhs.result, rhs.pos);
            } else {
                left = new Result(left.result / rhs.result, rhs.pos);
            }
        }
        return left;
    }

    private Result term(String s, int idx) {
        Result lhs = factor(s, idx);
        while(lhs.pos < s.length() && (s.charAt(lhs.pos) == '+' || s.charAt(lhs.pos) == '-')) {
            Result rhs = factor(s, lhs.pos + 1);
            if(s.charAt(lhs.pos) == '+') {
                lhs = new Result(lhs.result + rhs.result, rhs.pos);
            } else {
                lhs = new Result(lhs.result - rhs.result, rhs.pos);
            }
        }
        return lhs;
    }

    private class Result {
        int result;
        int pos;

        Result(int r, int pos) {
            this.result = r;
            this.pos = pos;
        }
    }
}
