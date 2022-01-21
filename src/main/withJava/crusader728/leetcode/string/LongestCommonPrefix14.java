package withJava.crusader728.leetcode.string;

public class LongestCommonPrefix14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) {
            return "";
        } else if(strs.length == 1) {
            return strs[0];
        } else {
            int len = strs[0].length();
            for(int i = 1; i < strs.length; ++i) {
                len = Math.min(go(strs[0], strs[i]), len);
            }
            return strs[0].substring(0, len);
        }
    }

    private int go(String s1, String s2) {
        int i = 0;
        while(i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        return i;
    }
}
