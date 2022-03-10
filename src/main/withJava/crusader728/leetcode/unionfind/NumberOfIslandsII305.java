package withJava.crusader728.leetcode.unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class NumberOfIslandsII305 {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DisjointSet disjointSet = new DisjointSet();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < positions.length; ++i) {
            int r = positions[i][0];
            int c = positions[i][1];
            int prc = disjointSet.parent(r * n + c);
            for(int[] delta: deltas) {
                int x = r + delta[0];
                int y = c + delta[1];
                if(x >= 0 && x < m && y >= 0 && y < n && disjointSet.hasKey(x * n + y)) {
                    int pxy = disjointSet.parent(x * n + y);
                    disjointSet.union(pxy, prc);
                }
            }
            result.add(disjointSet.count);

        }
        return result;
    }

    private static class DisjointSet {
        Map<Integer, Integer> parents = new HashMap<>();
        int count = 0;

        boolean hasKey(int x) {
            return parents.containsKey(x);
        }

        int parent(int n) {
            if(!parents.containsKey(n)) {
                parents.put(n, n);
                count++;
            }

            int p = parents.get(n);
            if(p == n) {
                return p;
            } else {
                parents.put(n, parent(p));
                return parents.get(n);
            }
        }

        void union(int x, int y) {
            int px = parent(x);
            int py = parent(y);
            if(px != py) {
                count--;
                parents.put(px, py);
            }
        }

    }


    private static int[][] deltas = new int[][] {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public static void main(String[] args) {
        NumberOfIslandsII305 numberOfIslandsII305 = new NumberOfIslandsII305();
        numberOfIslandsII305.numIslands2(3, 3, new int[][] {
                {0, 0},
                {0, 1},
                {1, 2},
                {1, 2}
        });
    }
}
