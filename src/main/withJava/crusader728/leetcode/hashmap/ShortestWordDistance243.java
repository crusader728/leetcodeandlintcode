package withJava.crusader728.leetcode.hashmap;

import java.util.ArrayList;
import java.util.List;

public class ShortestWordDistance243 {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        List<Integer> occurs1 = occurs(wordsDict, word1);
        List<Integer> occurs2 = occurs(wordsDict, word2);
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        while(i < occurs1.size() && j < occurs2.size()) {
            if(occurs1.get(i) < occurs2.get(j)) {
                min = Math.min(occurs2.get(j) - occurs1.get(i), min);
                i++;
            } else {
                min = Math.min(occurs1.get(i) - occurs2.get(j), min);
                j++;
            }
        }
        return min;
    }

    private List<Integer> occurs(String[] wordsDict, String word) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < wordsDict.length; ++i) {
            if(wordsDict[i].equals(word)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
