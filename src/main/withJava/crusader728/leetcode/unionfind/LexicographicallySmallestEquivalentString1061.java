package withJava.crusader728.leetcode.unionfind;

import java.util.HashMap;
import java.util.Map;

public class LexicographicallySmallestEquivalentString1061 {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        if(s1 == null || s2 == null || baseStr == null) {
            throw new IllegalArgumentException();
        }
        DisjointSet disjointSet = new DisjointSet();
        for(int i = 0; i < s1.length(); ++i) {
            disjointSet.union(s1.charAt(i), s2.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < baseStr.length(); ++i) {
            builder.append(disjointSet.find(baseStr.charAt(i)));
        }
        return builder.toString();
    }

    private static class DisjointSet {
        Map<Character, Character> parents;

        DisjointSet() {
            parents = new HashMap<>();
        }

        char find(char ch) {
            if(!parents.containsKey(ch)) {
                parents.put(ch, ch);
            }
            char p = parents.get(ch);
            if(p == ch) {
                return p;
            } else {
                parents.put(ch, find(p));
                return parents.get(ch);
            }
        }

        void union(char x, char y) {
            char px = find(x);
            char py = find(y);
            if(Character.compare(px, py) < 0) {
                parents.put(py, px);
            } else if(Character.compare(px, py) > 0) {
                parents.put(px, py);
            }
        }
    }
}
