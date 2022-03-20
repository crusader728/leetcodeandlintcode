package withJava.crusader728.leetcode.twopointer;

public class ValidWordAbbreviation408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while(i < word.length() && j < abbr.length()) {
            if(abbr.charAt(j) >= 'a' && abbr.charAt(j) <= 'z') {
                if(word.charAt(i) != abbr.charAt(j)) {
                    return false;
                } else {
                    i++;
                    j++;
                }
            } else if(abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                if(abbr.charAt(j) == '0') {
                    return false;
                }
                int len = 0;
                while(j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    len = len * 10 + abbr.charAt(j) - '0';
                    j++;
                }
                i += len;
            } else {
                return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }
}
