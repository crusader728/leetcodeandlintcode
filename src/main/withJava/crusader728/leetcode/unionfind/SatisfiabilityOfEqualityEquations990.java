package withJava.crusader728.leetcode.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SatisfiabilityOfEqualityEquations990 {
    private static class DisjoinSet {
        private Map<Character, Character> parents;

        DisjoinSet() {
            parents = new HashMap<>();
        }

        char find(char ch) {
            parents.putIfAbsent(ch, ch);
            char p = parents.get(ch);
            if(p == ch) {
                return p;
            } else {
                parents.put(ch, find(p));
                return parents.get(ch);
            }
        }

        boolean union(char x, char y) {
            char px = find(x);
            char py = find(y);
            if(px == py) {
                return false;
            } else {
                parents.put(px, py);
                return true;
            }
        }
    }
    public boolean equationsPossible(String[] equations) {
        DisjoinSet disjoinSet = new DisjoinSet();
        Arrays.stream(equations)
        .filter(eq -> eq.charAt(1) == '=')
        .forEach(eq -> {
            char l = eq.charAt(0);
            char r = eq.charAt(3);
            disjoinSet.union(l, r);
        });

        return Arrays.stream(equations)
        .filter(eq -> eq.charAt(1) == '!')
        .filter(eq -> {
            return disjoinSet.find(eq.charAt(0)) == disjoinSet.find(eq.charAt(3));
        })
        .findFirst()
        .isEmpty();
    }
}
