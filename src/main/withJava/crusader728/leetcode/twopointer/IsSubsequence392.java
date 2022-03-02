package withJava.crusader728.leetcode.twopointer;

public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        if(s == null || t == null) {
            throw new IllegalArgumentException();
        }
        if(s.isEmpty()) {
            return true;
        }
        if(t.isEmpty()) {
            return false;
        }

        int i = 0;
        int j = 0;
        while(i < s.length() && j < t.length()) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }

    }
}
