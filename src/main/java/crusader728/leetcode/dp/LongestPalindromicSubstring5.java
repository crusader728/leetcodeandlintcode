package java.crusader728.leetcode.dp;

public class LongestPalindromicSubstring5 {

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return null;
        }
        int maxLeft = 0;
        int maxRight = 0;
        int max = 1;
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for(int i = 0; i < s.length(); ++i) {
            isPalindrome[i][i] = true;
        }
        for(int i = 0; i + 1 < s.length(); ++i) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if(isPalindrome[i][i + 1]) {
                maxLeft = i;
                maxRight = i + 1;
                max = 1;
            }
        }

        for(int i = 0; i < s.length(); ++i) {
            //odd case
            for(int l = 1; l < s.length() && i - l >= 0 && i + l < s.length(); l++) {
                isPalindrome[i - l][i + l] = isPalindrome[i - l + 1][i + l - 1] && s.charAt(i - l) == s.charAt(i + l);
                if(isPalindrome[i - l][i + l] && max < 2 * l + 1) {
                    max = 2 * l + 1;
                    maxLeft = i - l;
                    maxRight = i + l;
                }
            }
            //even case
            for(int l = 1; l < s.length() && i - l >= 0 && i + 1 + l < s.length(); ++l) {
                isPalindrome[i - l][i + l + 1] = isPalindrome[i - l + 1][i + l] && s.charAt(i - l) == s.charAt(i + l + 1);
                if(isPalindrome[i - l][i + l + 1] && max < 2 * l + 2) {
                    max = 2 * l + 2;
                    maxLeft = i - l;
                    maxRight = i + l + 1;
                }
            }
        }

        return s.substring(maxLeft, maxRight + 1);
    }
}
