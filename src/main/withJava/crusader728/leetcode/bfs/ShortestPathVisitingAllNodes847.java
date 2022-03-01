package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShortestPathVisitingAllNodes847 {
    public int shortestPathLength(int[][] graph) {
        if(graph == null) {
            throw new IllegalArgumentException();
        }

        Deque<State> queue = new ArrayDeque<>();
        Set<State> seen = new HashSet<>();
        for(int i = 0; i < graph.length; ++i) {
            Set<Integer> visited = new HashSet<>();
            visited.add(i);
            State s = new State(i, visited, 0);
            seen.add(s);
            queue.offer(s);
        }

        while(!queue.isEmpty()) {
            State poll = queue.poll();
            if(poll.visited.size() == graph.length) {
                return poll.step;
            } else {
                int[] neighbors = graph[poll.current];
                for(int i = 0; i < neighbors.length; ++i) {
                    HashSet<Integer> newVisited = new HashSet<>(poll.visited);
                    newVisited.add(neighbors[i]);
                    State newState = new State(neighbors[i], newVisited, poll.step + 1);
                    if(!seen.contains(newState)) {
                        queue.offer(newState);
                        seen.add(newState);
                    }
                }
            }
        }
        return -1;
    }

    private static class State {
        int current;
        Set<Integer> visited;
        int step;

        public State(int i, Set<Integer> visited, int s) {
            this.current = i;
            this.visited = visited;
            this.step = s;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof State)) {
                return false;
            } else {
                State other = (State)obj;
                if(this.current != other.current) {
                    return false;
                } else {
                    return this.visited.containsAll(other.visited) && other.visited.containsAll(this.visited);
                }
            }
        }        

        @Override
        public int hashCode() {
            return Objects.hash(current, visited);
        }
    }

}
