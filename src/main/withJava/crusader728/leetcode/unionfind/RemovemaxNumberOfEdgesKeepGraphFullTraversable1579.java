package withJava.crusader728.leetcode.unionfind;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RemovemaxNumberOfEdgesKeepGraphFullTraversable1579 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DisjointSet alice = new DisjointSet(n);
        DisjointSet bob = new DisjointSet(n);

        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
            
        }.reversed());

        int count = 0;

        for(int i = 0; i < edges.length; ++i) {
            int u = edges[i][1];
            int v = edges[i][2];
            if(edges[i][0] == 1) {
                if(alice.union(u, v)) {
                    count++;
                }
            } else if(edges[i][0] == 2) {
                if(bob.union(u, v)) {
                    count++;
                }
            } else {
                boolean aliceUnion = alice.union(u, v);
                boolean bobUnion = bob.union(u, v);
                if(aliceUnion || bobUnion) {
                    count++;
                }
            }
        }
        if(alice.total == 1 && bob.total == 1) {
            return edges.length - count;
        } else {
            return -1;
        }
    }

    private static class DisjointSet {
        private Map<Integer, Integer> parents;
        private int total;

        DisjointSet(int n) {
            parents = new HashMap<>();
            for(int i = 1; i <= n; ++i) {
                parents.put(i, i);
            }
            total = n;
        }

        int find(int n) {
            int p = parents.get(n);
            if(p == n) {
                return p;
            } else {
                parents.put(n, find(p));
                return parents.get(n);
            }
        }

        boolean union(int x, int y) {
            int parentx = find(x);
            int parenty = find(y);
            if(parentx != parenty) {
                parents.put(Math.max(parentx, parenty), Math.min(parentx, parenty));
                total--;
                return true;
            } else {
                return false;
            }
        }
    }
}
