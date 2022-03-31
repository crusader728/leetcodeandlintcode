package withJava.crusader728.leetcode.unionfind;

import java.util.*;

public class MakeALargeIsland827 {
    public int largestIsland(int[][] grid) {
        DisjointSet disjointSet = new DisjointSet();
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 1) {
                    dfs(grid, i, j, disjointSet);
                }
            }
        }
        int max = 0;
        for(int area: disjointSet.idAreaMapping.values()) {
            max = Math.max(max, area);
        }
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 0) {
                    Set<Integer> neighbors = new HashSet<>();
                    for(int[] delta: deltas) {
                        int x = i + delta[0];
                        int y = j + delta[1];
                        if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                            neighbors.add(disjointSet.find(x * n + y));
                        }
                    }
                    max = Math.max(max, 1 + neighbors.stream().map(neighbor -> disjointSet.idAreaMapping.get(neighbor)).reduce(0, (a, b) -> a + b));
                }
            }
        }
        return max;
    }

    private void dfs(int[][] grid, int i, int j, DisjointSet disjointSet) {
        int pij = disjointSet.find(i * grid[0].length + j);
        for(int[] delta: deltas) {
            int x = i + delta[0];
            int y = j + delta[1];
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[x].length && grid[x][y] == 1) {
                int pxy = disjointSet.find(x * grid[0].length + y);
                if(pij != pxy) {
                    disjointSet.union(pij, pxy);
                }
                dfs(grid, x, y, disjointSet);
            }
        }
    }

    private static int[][] deltas = new int[][] {
        {0, 1},
        {0, -1},
        {-1, 0},
        {1, 0}
    };

    private static class DisjointSet {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> idAreaMapping;

        DisjointSet() {
            this.parents = new HashMap<>();
            this.idAreaMapping = new HashMap<>();
        }

        Integer find(int x) {
            if(parents.containsKey(x)) {
                int p = parents.get(x);
                if(p != x) {
                    parents.put(x, find(p));
                }
                return parents.get(x);
            } else {
                parents.put(x, x);
                idAreaMapping.put(x, 1);
                return x;
            }
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if(px != py) {
                parents.put(px, py);
                idAreaMapping.put(py, idAreaMapping.get(px) + idAreaMapping.get(py));
            }
        }
    }
}
