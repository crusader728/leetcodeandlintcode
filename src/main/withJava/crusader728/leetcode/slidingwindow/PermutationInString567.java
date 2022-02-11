package withJava.crusader728.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString567 {
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> occurs = new HashMap<>();
        for(int i = 0; i < s1.length(); ++i) {
            char ch = s1.charAt(i);
            occurs.put(ch, occurs.getOrDefault(ch, 0) + 1);
        }

        int l = 0;
        int r = 0;
        Map<Character, Integer> window = new HashMap<>();
        while(r < s2.length()) {
            char ch = s2.charAt(r);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
            while(l < s2.length() && r - l + 1 > s1.length()) {
                ch = s2.charAt(l);
                window.put(ch, window.get(ch) - 1);
                if(window.get(ch) == 0) {
                    window.remove(ch);
                }
                l++;
            }
            if(r - l + 1 == s1.length()) {
                if(occurs.equals(window)) {
                    return true;
                }
            }
            r++;
        }
        return false;
    }
}
