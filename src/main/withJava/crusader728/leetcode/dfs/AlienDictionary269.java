package withJava.crusader728.leetcode.dfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AlienDictionary269 {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> lt = new HashMap<>();
        Set<Character> chars = new HashSet<>();
        for(int i = 0; i < words.length - 1; ++i) {
            String x = words[i];
            String y = words[i + 1];
            go(x, y, lt, chars);
        }

        return topo(chars, lt);
    }

    private String topo(Set<Character> chars, Map<Character, Set<Character>> lt) {
        Map<Character, Integer> color = new HashMap<>();
        for(char ch: chars) {
            color.put(ch, 0);
        }

        StringBuilder builder = new StringBuilder();
        for(char ch: chars) {
            if(color.get(ch) == 0) {
                boolean hasCycle = dfs(chars, lt, color, ch, builder);
                if(hasCycle) {
                    return "";
                }
            }
        }
        return builder.reverse().toString();
    }

    private boolean dfs(Set<Character> chars, Map<Character, Set<Character>> lt, Map<Character, Integer> color, char ch, StringBuilder builder) {
        color.put(ch, 1);
        for(char neighbor: lt.getOrDefault(ch, Collections.emptySet())) {
            if(color.get(neighbor) == 1) {
                return true;
            } else if(color.get(neighbor) == 0) {
                boolean hasCycle = dfs(chars, lt, color, neighbor, builder);
                if(hasCycle) {
                    return true;
                }
            }
        }
        color.put(ch, 2);
        builder.append(ch);
        return false;
    }

    private void go(String x, String y, Map<Character, Set<Character>> lt, Set<Character> chars) {
        int i = 0;
        int j = 0;
        while(i < x.length() && j < y.length() && x.charAt(i) == y.charAt(j)) {
            i++;
            j++;
        }
        if(i < x.length() && j < y.length()) {
            lt.computeIfAbsent(x.charAt(i), k -> new HashSet<>()).add(y.charAt(j));
        }
        for(int idx = 0; idx < x.length(); ++idx) {
            chars.add(x.charAt(idx));
        }
        for(int idx = 0; idx < y.length(); ++idx) {
            chars.add(y.charAt(idx));
        }
    }

    public static void main(String[] args) {
        AlienDictionary269 alienDictionary269 = new AlienDictionary269();
        System.out.println(alienDictionary269.alienOrder(new String[] {"z","x","z"}));
    }
}
