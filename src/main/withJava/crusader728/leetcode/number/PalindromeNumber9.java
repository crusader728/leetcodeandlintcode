package withJava.crusader728.leetcode.number;

public class PalindromeNumber9 {
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        } else if(x < 10) {
            return true;
        } else if(x % 10 == 0) {
            return false;
        } else {
            int reverted = 0;
            while(x > reverted) {
                reverted = reverted * 10 + x % 10;
                x = x / 10;
            }
            return x == reverted || reverted / 10 == x;
        }
    }
}
