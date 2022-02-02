package withJava.crusader728.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInString438 {
    public List<Integer> findAnagrams(String s, String p) {
        if(s == null || p == null) {
            return new ArrayList<>();
        }
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < p.length(); ++i) {
            char ch = p.charAt(i);
            if(!pCount.containsKey(ch)) {
                pCount.put(ch, 0);
            }
            pCount.put(ch, pCount.get(ch) + 1);
        }

        int l = 0;
        int r = 0;
        while(r < s.length()) {
            char ch = s.charAt(r);
            if(!sCount.containsKey(ch)) {
                sCount.put(ch, 0);
            }
            sCount.put(ch, sCount.get(ch) + 1);
            while(l < s.length() && r - l + 1 > p.length()) {
                char left = s.charAt(l);
                sCount.put(left, sCount.get(left) - 1);
                if(sCount.get(left) == 0) {
                    sCount.remove(left);
                }
                l++;
            }
            if(sCount.equals(pCount)) {
                result.add(l);
            }
            r++;
        }
        return result;
    }
}
