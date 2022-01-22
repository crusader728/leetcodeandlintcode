package withJava.crusader728.leetcode.twopointer;

public class ValidatePalindromeII680 {
    public boolean validPalindrome(String s) {
        if(s == null || s.length() == 0) {
            return false;
        }
        int low = 0;
        int high = s.length() - 1;
        while(low <= high) {
            if(s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            } else {
                return isPalindrome(s, low + 1, high) || isPalindrome(s, low, high - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while(l <= r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
