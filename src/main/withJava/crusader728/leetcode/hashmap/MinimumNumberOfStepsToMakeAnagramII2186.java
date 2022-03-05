package withJava.crusader728.leetcode.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumNumberOfStepsToMakeAnagramII2186 {
    public int minSteps(String s, String t) {
        Map<Character, Integer> occurS = countOccur(s);
        Map<Character, Integer> occurT = countOccur(t);

        Map<Character, Integer> merged = merge(occurS, occurT);
        int count = 0;
        for(char ch: merged.keySet()) {
            count += merged.get(ch) - occurS.getOrDefault(ch, 0);
            count += merged.get(ch) - occurT.getOrDefault(ch, 0);
        }
        return count;
    }

    private Map<Character, Integer> merge(Map<Character, Integer> occurS, Map<Character, Integer> occurT) {
        Map<Character, Integer> result = new HashMap<>();
        Set<Character> keys = new HashSet<>();
        keys.addAll(occurS.keySet());
        keys.addAll(occurT.keySet());
        for(Character ch: keys) {
            result.put(ch , Math.max(occurS.getOrDefault(ch, 0), occurT.getOrDefault(ch, 0)));
        }
        return result;
    }

    private Map<Character, Integer> countOccur(String t) {
        Map<Character, Integer> result = new HashMap<>();
        for(int i = 0; i < t.length(); ++i) {
            result.put(t.charAt(i), result.getOrDefault(t.charAt(i), 0) + 1);
        }
        return result;
    }
}
