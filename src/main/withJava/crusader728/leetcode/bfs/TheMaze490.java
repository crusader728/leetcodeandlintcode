package withJava.crusader728.leetcode.bfs;

import java.util.*;
import java.util.function.BiFunction;

public class TheMaze490 {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }

        Queue<State> Q = new LinkedList<>();
        Set<State> S = new HashSet<>();
        State initial = new State(start[0], start[1], 0);
        S.add(initial);
        Q.offer(initial);
        while(!Q.isEmpty()) {
            State s = Q.poll();
            List<State> candidates = getCandidates(maze, s);
            for(State candidate: candidates) {
                if(!S.contains(candidate)) {
                    if(candidate.x == destination[0] && candidate.y == destination[1]) {
                        return true;
                    } else {
                        S.add(candidate);
                        Q.offer(candidate);
                    }
                }
            }
        }
        return false;
    }

    private List<State> getCandidates(int[][] maze, State s) {
        List<State> result = new ArrayList<>();
        for(BiFunction<int[][], State, State> f: gens) {
            result.add(f.apply(maze, s));
        }
        return result;
    }

    private static final List<BiFunction<int[][], State, State>> gens;
    
    static {
        gens = new ArrayList<>();
        gens.add(new BiFunction<int[][],State,State>() {

            @Override
            public State apply(int[][] t, State u) {
                // go North
                int current = u.x;
                while(current - 1 >= 0 && t[current - 1][u.y] == 0) {
                    current = current - 1;
                }
                return new State(current, u.y, u.step + 1);
            }
            
        });
        gens.add(new BiFunction<int[][],State,State>() {

            @Override
            public State apply(int[][] t, State u) {
                // go South
                int current = u.x;
                while(current + 1 < t.length && t[current + 1][u.y] == 0) {
                    current = current + 1;
                }
                return new State(current, u.y, u.step + 1);
            }
            
        });
        gens.add(new BiFunction<int[][],State,State>() {

            @Override
            public State apply(int[][] t, State u) {
                // go west
                int current = u.y;
                while(current - 1 >= 0 && t[u.x][current - 1] == 0) {
                    current = current - 1;
                }

                return new State(u.x, current, u.step + 1);
            }
            
        });
        gens.add(new BiFunction<int[][], State, State>() {

            @Override
            public State apply(int[][] t, State u) {
                // go east
                int current = u.y;
                while(current + 1 < t[u.x].length && t[u.x][current + 1] == 0) {
                    current = current + 1;
                }
                return new State(u.x, current, u.step + 1);
            }

        });
    }

    private static class State {
        int x;
        int y;
        int step;

        State(int x, int y, int s) {
            this.x = x;
            this.y = y;
            this.step = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return x == state.x &&
                    y == state.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
