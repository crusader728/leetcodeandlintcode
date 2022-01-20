package java.crusader728.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters3 {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> seen = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if(seen.containsKey(ch)) {
                int prevIdx = seen.get(ch);
                while(left <= prevIdx) {
                    seen.remove(s.charAt(left));
                    left++;
                }
            }
            right = i;
            seen.put(ch, i);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
