package withJava.crusader728.leetcode.parser;

public class ValidNumber65 {
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }

        Result base = base(s, 0);
        if(base == null) {
            return false;
        }

        Result exp = exp(s, base.pos);
        if(exp == null) {
            return false;
        } else if(exp.res.equals("")) {
            return exp.pos == s.length();
        } else {
            return exp.pos == s.length();
        }
    }

    private Result base(String s, int pos) {
        StringBuilder builder = new StringBuilder();
        int idx = pos;
        boolean seenSign = false;
        boolean seenDot = false;
        boolean seenDigits = false;
        while(idx < s.length()) {
            if(s.charAt(idx) == '-' || s.charAt(idx) == '+') {
                if(!seenSign && !seenDigits && !seenDot) {
                    seenSign = true;
                    builder.append(s.charAt(idx));
                    idx++;
                } else {
                    return null;
                }
            } else if(s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                while(idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                    builder.append(s.charAt(idx));
                    idx++;
                }
                seenDigits = true;
            } else if(s.charAt(idx) == '.') {
                if(!seenDot) {
                    builder.append(s.charAt(idx));
                    seenDot = true;
                    idx++;
                } else {
                    return null;
                }
            } else {
                break;
            }
        }
        if(seenDigits) {
            return new Result(idx, builder.toString());
        } else {
            return null;
        }
    }

    private Result exp(String s, int pos) {
        StringBuilder builder = new StringBuilder();
        int idx = pos;
        boolean seenE = false;
        boolean seenDigits = false;
        boolean seenSign = false;
        while(idx < s.length()) {
            if(s.charAt(idx) == 'e' || s.charAt(idx) == 'E') {
                if(!seenE) {
                    builder.append(s.charAt(idx));
                    idx++;
                    seenE = true;
                } else {
                    return null;
                }
            } else if(s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                if(seenE) {
                    while(idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                        builder.append(s.charAt(idx));
                        idx++;
                    }
                    seenDigits = true;
                } else {
                    return null;
                }
            } else if(s.charAt(idx) == '+' || s.charAt(idx) == '-') {
                if(seenE && !seenSign && !seenDigits) {
                    builder.append(s.charAt(idx));
                    idx++;
                    seenSign = true;
                } else {
                    return null;
                }
            } else {
                break;
            }
        }
        if(seenE == false && builder.length() == 0) {
            return new Result(idx, builder.toString());
        } else if(seenE == false) {
            return null;
        } else if(seenDigits == false) {
            return null;
        } else {
            return new Result(idx, builder.toString());
        }
    }

    private static class Result {
        int pos;
        String res;

        Result(int p, String r) {
            this.pos = p;
            this.res = r;
        }
    }

    public static void main(String[] args) {
        ValidNumber65 validNumber65 = new ValidNumber65();
        validNumber65.isNumber("005047e+6");
    }

}
