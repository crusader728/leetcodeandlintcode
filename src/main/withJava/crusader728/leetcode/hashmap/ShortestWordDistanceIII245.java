package withJava.crusader728.leetcode.hashmap;

import java.util.*;

public class ShortestWordDistanceIII245 {
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        Map<String, List<Integer>> wordIdxMapping = new HashMap<>();
        for(int i = 0; i < wordsDict.length; ++i) {
            wordIdxMapping.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }

        if(word1.equals(word2)) {
            List<Integer> l = wordIdxMapping.get(word1);
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < l.size() - 1; ++i) {
                min = Math.min(min, l.get(i + 1) - l.get(i));
            }
            return min;
        } else {
            int min = Integer.MAX_VALUE;
            List<Integer> l1 = wordIdxMapping.get(word1);
            List<Integer> l2 = wordIdxMapping.get(word2);
            int i = 0;
            int j = 0;
            while(i < l1.size() && j < l2.size()) {
                min = Math.min(min, Math.abs(l1.get(i) - l2.get(j)));
                if(l1.get(i) < l2.get(j)) {
                    i++;
                } else {
                    j++;
                }
            }
            return min;
        }
    }
}
