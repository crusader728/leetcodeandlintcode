package java.crusader728.lintcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ID795_UniquePaths {
    /**
     * @param m: the row
     * @param n: the column
     * @return: the possible unique paths
     */

    private int[] deltax = new int[] {-1, 1, 0, 0};
    private int[] deltay = new int[] {0, 0, -1, 1};
    public int uniquePaths(int m, int n) {
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        visited.put(0, new HashSet<>());
        visited.get(0).add(0);
        return helper(m, n, 0, 0, visited);
    }

    private int helper(int m, int n, int x, int y, Map<Integer, Set<Integer>> visited) {
        if(x == m - 1 && y == n - 1) {
            return 1;
        } else {
            int sum = 0;
            for(int i = 0; i < deltax.length; ++i) {
                int newX = x + deltax[i];
                int newY = y + deltay[i];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if(!visited.containsKey(newX)) {
                        visited.put(newX, new HashSet<>());
                    }
                    if(!visited.get(newX).contains(newY)) {
                        visited.get(newX).add(newY);
                        sum += helper(m, n, newX, newY, visited);
                        visited.get(newX).remove(newY);
                    }
                }
            }
            return sum;
        }
    }
}
