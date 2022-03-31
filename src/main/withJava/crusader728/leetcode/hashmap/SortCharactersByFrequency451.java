package withJava.crusader728.leetcode.hashmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharactersByFrequency451 {
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for(char ch: s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        List<Character> keys = new ArrayList<>();
        keys.addAll(freq.keySet());
        Collections.sort(keys, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return Integer.compare(freq.get(c1), freq.get(c2));     
            }
        }.reversed());
        StringBuilder builder = new StringBuilder();
        for(char ch: keys) {
            for(int i = 0; i < freq.get(ch); ++i) {
                builder.append(ch);
            }
        }
        return builder.toString();
    }
}
