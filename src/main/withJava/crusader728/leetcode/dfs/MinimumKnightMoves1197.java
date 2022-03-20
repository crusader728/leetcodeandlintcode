package withJava.crusader728.leetcode.dfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MinimumKnightMoves1197 {
    private static int[][] deltas = new int[][] {
        {-2, -1},
        {-1, -2},
        {1, -2},
        {2, -1},
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1}
    };
    public int minKnightMoves(int x, int y) {
        Deque<Cord> q = new ArrayDeque<>();
        Set<Cord> visited = new HashSet<>();
        q.offer(new Cord(0, 0, 0));
        while(!q.isEmpty()) {
            Cord poll = q.poll();
            if(poll.r == x && poll.c == y) {
                return poll.step;
            } 
            visited.add(poll);
            for(int[] delta: deltas) {
                int newR = poll.r + delta[0];
                int newC = poll.c + delta[1];
                Cord newCord = new Cord(newR, newC, poll.step + 1);
                if(!visited.contains(newCord)) {
                    q.offer(newCord);
                }
            }
        }
        return -1;
    }

    private static class Cord {
        int r;
        int c;
        int step;

        Cord(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.step = s;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Cord)) {
                return false;
            } else {
                Cord other = (Cord)obj;
                return this.r == other.r && this.c == other.c;
            }
        }
    }
}
