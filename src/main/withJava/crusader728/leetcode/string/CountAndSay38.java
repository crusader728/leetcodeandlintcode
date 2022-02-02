package withJava.crusader728.leetcode.string;

public class CountAndSay38 {
    public String countAndSay(int n) {
        if(n == 1) {
            return "1";
        } else {
            String s = countAndSay(n - 1);
            return count(s);
        }
    }

    private String count(String s) {
        int i = 0;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        while(i < s.length()) {
            int digit = s.charAt(i) - '0';
            for(int j = i; j < s.length() && s.charAt(j) == s.charAt(i); ++j) {
                count++;
            }
            builder.append(String.valueOf(count));
            builder.append((char)('0' + digit));
            i += count;
            count = 0;
        }
        return builder.toString();
    }
}
