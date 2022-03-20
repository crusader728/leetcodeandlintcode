package withJava.crusader728.leetcode.divideandconquer;

public class LongestCommonPrefix14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) {
            return strs[0];
        } else {
            int result = helper(strs, 0, strs.length);
            if(result == 0) {
                return "";
            } else {
                return strs[0].substring(0, result);
            }
        }
    }


    private int helper(String[] strs, int l, int r) {
        if(l >= r) {
            return Integer.MAX_VALUE;
        } else if(l == r - 1) {
            return strs[l].length();
        }
        int mid = l + (r - l) / 2;
        int lr = helper(strs, l, mid);
        int rr = helper(strs, mid, r);
        return go(strs[l], strs[mid], lr, rr);
    }


    private int go(String s1, String s2, int l, int r) {
        int i = 0;
        while(i < l && i < r && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        return i;
    }
}
