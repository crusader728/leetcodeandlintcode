package withJava.crusader728.leetcode.twopointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistanceII244 {
    static class WordDistance {
        private Map<String, List<Integer>> wordIdxMap = new HashMap<>();

        public WordDistance(String[] wordsDict) {
            for(int i = 0; i < wordsDict.length; ++i) {
                wordIdxMap.putIfAbsent(wordsDict[i], new ArrayList<>());
                wordIdxMap.get(wordsDict[i]).add(i);
            }
        }
        
        public int shortest(String word1, String word2) {
            List<Integer> l1 = wordIdxMap.get(word1);
            List<Integer> l2 = wordIdxMap.get(word2);
            int i = 0;
            int j = 0;
            int min = Integer.MAX_VALUE;
            while(i < l1.size() && j < l2.size()) {
                int p1 = l1.get(i);
                int p2 = l2.get(j);
                if(p1 > p2) {
                    min = Math.min(p1 - p2, min);
                    j++;
                } else {
                    min = Math.min(p2 - p1, min);
                    i++;
                }
            }
            return min;
        }
    }
}
