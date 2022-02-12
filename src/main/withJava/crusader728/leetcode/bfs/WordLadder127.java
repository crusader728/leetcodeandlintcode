package withJava.crusader728.leetcode.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class WordLadder127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) {
            return 0;
        }

        Map<String, Set<String>> wordToPatterns = new HashMap<>();
        Map<String, Set<String>> patternToWords = new HashMap<>();

        buildEdges(beginWord, wordList, wordToPatterns, patternToWords);

        Deque<State> queue = new ArrayDeque<>();
        Set<String> visited =new HashSet<>();
        queue.offer(new State(beginWord, 1));
        visited.add(beginWord);
        while(!queue.isEmpty()) {
            State state = queue.poll();
            if(state.word.equals(endWord)) {
                return state.count;
            } else {
                for(String next: neighbors(state.word, wordToPatterns, patternToWords)) {
                    if(!visited.contains(next)) {
                        queue.offer(new State(next, state.count + 1));
                        visited.add(next);
                    }
                }
            }
        }
        return 0;
    }

    private List<String> neighbors(String word, Map<String, Set<String>> wordToPatterns,
            Map<String, Set<String>> patternToWords) {
        List<String> result = new ArrayList<>();
        for(String p: wordToPatterns.getOrDefault(word, Collections.emptySet())) {
            for(String w: patternToWords.getOrDefault(p, Collections.emptySet())) {
                if(!w.equals(word)) {
                    result.add(w);
                }
            }
        }
        return result;
    }

    private void buildEdges(String beginWord, List<String> wordList, Map<String, Set<String>> wordToPatterns,
            Map<String, Set<String>> patternToWords) {
                Stream.concat(Stream.of(beginWord), wordList.stream())
                .forEach(w -> {
                    StringBuilder builder = new StringBuilder(w);
                    for(int i = 0; i < builder.length(); ++i) {
                        char ch = builder.charAt(i);
                        builder.setCharAt(i, '*');
                        String pattern = builder.toString();
                        wordToPatterns.computeIfAbsent(w, k -> new HashSet<>()).add(pattern);
                        patternToWords.computeIfAbsent(pattern, k -> new HashSet<>()).add(w);
                        builder.setCharAt(i, ch);
                    }
                });
    }

    private static class State {
        private String word;
        private int count;

        State(String w, int c) {
            this.word = w;
            this.count = c;
        }
    }
}
