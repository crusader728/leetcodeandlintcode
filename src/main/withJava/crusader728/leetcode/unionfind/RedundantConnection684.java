package withJava.crusader728.leetcode.unionfind;

import java.util.HashMap;
import java.util.Map;

public class RedundantConnection684 {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, Integer> parent = new HashMap<>();
        for(int i = 1; i <= n; ++i) {
            parent.put(i, i);
        }

        int[] result = null;
        for(int[] edge: edges) {
            int a = edge[0];
            int b = edge[1];
            int parentA = find(parent, a);
            int parentB = find(parent, b);
            if(parentA == parentB) {
                result = edge;
            } else if(parentA < parentB) {
                parent.put(parentB, parentA);
            } else {
                parent.put(parentA, parentB);
            }
        }
        return result;
    }

    private int find(Map<Integer, Integer> parent, int a) {
        int p = parent.get(a);
        if(p != a) {
            parent.put(a, find(parent, p));
            return parent.get(a);
        } else {
            return p;
        }
    }
}
