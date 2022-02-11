package withJava.crusader728.leetcode.bfs;

import java.util.*;

public class ShortestPathInGridWithObstaclesElimination1293 {
    public int shortestPath(int[][] grid, int k) {
        if (k >= grid.length + grid[0].length - 2) {
            return grid.length + grid[0].length - 2;
        }
        
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

        State init = new State(0, 0, k, 0);
        queue.offer(init);
        visited.add(init);
        while(!queue.isEmpty()) {
            State state = queue.poll();
            if(state.row == grid.length - 1 && state.col == grid[grid.length - 1].length - 1) {
                return state.step;
            } else {
                for(int[] delta: deltas) {
                    int x = state.row + delta[0];
                    int y = state.col + delta[1];
                    if(x >= 0 && x < grid.length && y >= 0 && y < grid[x].length) {
                        State next = null;
                        if(grid[x][y] == 1 && state.quota > 0) {
                            next = new State(x, y, state.quota - 1, state.step + 1);
                        } else if(grid[x][y] == 0) {
                            next = new State(x, y, state.quota, state.step + 1);
                        }
                        if(next != null && !visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
        }
        return -1;
    }

    private int[][] deltas = new int[][] {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };

    private static class State {
        int row;
        int col;
        int quota;
        int step;

        State(int r, int c, int q, int s) {
            this.row = r;
            this.col = c;
            this.quota = q;
            this.step = s;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col, quota);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof State)) {
                return false;
            }
            State other = (State)obj;
            return other.row == this.row && other.col == this.col && other.quota == this.quota;
        }
    }

    public static void main(String[] args) {
        ShortestPathInGridWithObstaclesElimination1293 shortestPathInGridWithObstaclesElimination1293 = new ShortestPathInGridWithObstaclesElimination1293();
        int result = shortestPathInGridWithObstaclesElimination1293.shortestPath(new int[][]{
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        }, 1);
        System.out.println(result);
    }
}
