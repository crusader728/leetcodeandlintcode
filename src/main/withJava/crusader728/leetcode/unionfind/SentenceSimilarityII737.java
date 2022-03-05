package withJava.crusader728.leetcode.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SentenceSimilarityII737 {
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if(sentence1.length != sentence2.length) {
            return false;
        }

        DisjointSet<String> disjointSet = new DisjointSet<>();
        for(List<String> pair: similarPairs) {
            String x = pair.get(0);
            String y = pair.get(1);
            disjointSet.union(x, y);
        }

        for(int i = 0; i < sentence1.length; ++i) {
            String x = sentence1[i];
            String y = sentence2[i];
            String px = disjointSet.find(x);
            String py = disjointSet.find(y);
            if(!px.equals(py)) {
                return false;
            }
        }
        return true;
    }

    private static class DisjointSet<T> {
        private Map<T, T> parents;

        DisjointSet() {
            this.parents = new HashMap<>();
        }

        T find(T element) {
            if(!parents.containsKey(element)) {
                parents.put(element, element);
            }

            T p = parents.get(element);
            if(p.equals(element)) {
                return p;
            } else {
                parents.put(element, find(p));
                return parents.get(element);
            }
        }

        void union(T x, T y) {
            T px = find(x);
            T py = find(y);
            if(!px.equals(py)) {
                parents.put(px, py);
            }
        }
    }
}
