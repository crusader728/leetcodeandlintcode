package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle773 {
    public int slidingPuzzle(int[][] board) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        String finalState = "123450";
        queue.offer(toState(board));
        int result = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; ++i) {
                String state = queue.poll();
                if(state.equals(finalState)) {
                    return result;
                }
                visited.add(state);
                for(String s: neighborState(state)) {
                    if(!visited.contains(s)) {
                        queue.offer(s);
                    }
                }
            }
            result++;
        }
        return -1;
    }

    private static final int[][] swaps = new int[][] {
        {1, 3},
        {0, 2, 4},
        {1, 5},
        {0, 4},
        {1, 3, 5},
        {2, 4}
    };

    private List<String> neighborState(String state) {
        List<String> result = new ArrayList<>();
        int indexOf = state.indexOf('0');
        if(indexOf < 0 || indexOf >= swaps.length) {
            throw new IllegalStateException();
        }
        for(int target: swaps[indexOf]) {
            result.add(swap(state, indexOf, target));
        }
        return result;
    }

    private String swap(String state, int indexOf, int target) {
        StringBuilder builder = new StringBuilder();
        builder.setCharAt(indexOf, state.charAt(target));
        builder.setCharAt(target, state.charAt(indexOf));
        return builder.toString();
    }

    private String toState(int[][] board) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                builder.append(String.valueOf(board[i][j]));
            }
        }
        return builder.toString();
    }
}
