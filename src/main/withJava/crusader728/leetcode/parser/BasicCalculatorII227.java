package withJava.crusader728.leetcode.parser;

public class BasicCalculatorII227 {
    public int calculate(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        } 
        String replaced = s.replaceAll("\\s+", "");
        return term(replaced, 0).r;
    }

    private Result number(String s, int i) {
        int sum = 0;
        int p = i;
        while(p < s.length() && s.charAt(p) >= '0' && s.charAt(p) <= '9') {
            sum = sum * 10 + s.charAt(p) - '0';
            p++;
        }
        return new Result(p, sum);
    }

    private Result term(String s, int i) {
        Result left = factor(s, i);
        while(left.pos < s.length() && (s.charAt(left.pos) == '+' || s.charAt(left.pos) == '-')) {
            Result rhs = factor(s, left.pos + 1);
            int newSum = s.charAt(left.pos) == '+' ? (left.r + rhs.r) : (left.r - rhs.r);
            left = new Result(rhs.pos, newSum);
        }
        return left;
    }

    private Result factor(String s, int i) {
        Result lhs = number(s, i);
        while(lhs.pos < s.length() && (s.charAt(lhs.pos) == '*' || s.charAt(lhs.pos) == '/')) {
            Result rhs = number(s, lhs.pos + 1);
            int newSum = s.charAt(lhs.pos) == '*' ? (lhs.r * rhs.r) : (lhs.r / rhs.r);
            lhs = new Result(rhs.pos, newSum);
        }
        return lhs;
    }

    private static class Result {
        int pos;
        int r;

        Result(int p, int res) {
            this.pos = p;
            this.r = res;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "pos=" + pos +
                    ", r=" + r +
                    '}';
        }
    }

    public static void main(String[] args) {
        BasicCalculatorII227 basicCalculatorII227 = new BasicCalculatorII227();
        System.out.println(basicCalculatorII227.calculate("3 + 2 * 2"));
    }
}
