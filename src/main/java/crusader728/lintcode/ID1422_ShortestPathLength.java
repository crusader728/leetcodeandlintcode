package java.crusader728.lintcode;

import java.lang.reflect.Array;
import java.util.*;

public class ID1422_ShortestPathLength {
    class State {
        Integer current;
        List<Integer> path;
        Set<Integer> visited;
    }

    public int shortestPathLength(int[][] graph) {
        Queue<State> queue = new LinkedList<>();
        Map<Integer, Map<Set<Integer>, List<Integer>>> visited = new HashMap<>();
        int n = graph.length;
        for(int i = 0; i < n; ++i) {
            State state = new State();
            state.current = i;
            state.path = new ArrayList<>();
            state.path.add(i);
            state.visited = new HashSet<>();
            state.visited.add(i);
            queue.offer(state);
        }
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            State current = queue.poll();
            int node = current.current;
            if(current.visited.size() == n) {
                min = current.path.size() - 1;
                break;
            }
            for(int i = 0; i < graph[node].length; ++i) {
                int next = graph[node][i];
                if(!visited.containsKey(next)) {
                    visited.put(next, new HashMap<>());
                }
                Set<Integer> v = new HashSet<>(current.visited);
                v.add(next);
                if(!visited.get(next).containsKey(v)) {
                    List<Integer> path = new ArrayList<>(current.path);
                    path.add(next);
                    visited.get(next).put(v, path);
                    State newState = new State();
                    newState.current = next;
                    newState.path = path;
                    newState.visited = v;
                    queue.offer(newState);
                }
            }
        }
        return min;
    }
}
