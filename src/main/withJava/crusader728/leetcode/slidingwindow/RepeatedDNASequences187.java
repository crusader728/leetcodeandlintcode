package withJava.crusader728.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences187 {
    public List<String> findRepeatedDnaSequences(String s) {
        int l = 0;
        int r = 0;
        Set<String> ans = new HashSet<>();
        Set<String> seen = new HashSet<>();
        while(r < s.length()) {
            if(r - l + 1 < 10) {
                r++;
            } else {
                String sequence = s.substring(l, r + 1);
                if(seen.contains(sequence)) {
                    ans.add(sequence);
                } else {
                    seen.add(sequence);
                }
                l++;
            }

        }
        return new ArrayList<>(ans);
    }
}
