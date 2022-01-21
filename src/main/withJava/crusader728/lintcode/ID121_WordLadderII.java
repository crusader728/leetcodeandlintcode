package withJava.crusader728.lintcode;

import java.util.*;
import java.util.stream.Collectors;

public class ID121_WordLadderII {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: a list of lists of string
     */

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        Map<String, Set<String>> graph = buildGraph(start, end, dict);
        List<List<String>> result = new ArrayList<>();
        bfs(start, end, graph, result);
        return result;
    }

    private void bfs(String start, String end, Map<String, Set<String>> graph, List<List<String>> result) {
        Queue<List<String>> queue = new ArrayDeque<>();
        Map<String, Integer> dist = new HashMap<>();
        List<String> init = new LinkedList<>();
        init.add(0, end);
        dist.put(end, 0);
        queue.offer(init);

        int minLength = -1;
        while(!queue.isEmpty()) {
            List<String> top = queue.poll();
            if(top.get(0).equals(start)) {
                if(minLength == -1) {
                    minLength = top.size();
                    result.add(top);
                } else if(top.size() == minLength) {
                    result.add(top);
                } else {
                    break;
                }
            }
            if(minLength != -1 && top.size() > minLength) {
                break;
            }
            for(String next: graph.getOrDefault(top.get(0), Collections.emptySet())) {
                Integer lastDist = dist.get(top.get(0));
                Integer nextDist = dist.get(next);
                if(!top.contains(next) && nextDist == null) {
                    List<String> newPath = new LinkedList<>(top);
                    newPath.add(0, next);
                    queue.offer(newPath);
                    dist.put(next, lastDist + 1);
                }
            }
        }
    }

    private Map<String, Set<String>> buildGraph(String start, String end, Set<String> dict) {
        Set<String> wordSet = new HashSet<>(dict);
        wordSet.add(start);
        wordSet.add(end);
        Map<String, Set<String>> wordToPattern = new HashMap<>();
        Map<String, Set<String>> patternToWord = new HashMap<>();
        for(String word: wordSet) {
            for(int i = 0; i < word.length(); ++i) {
                StringBuilder builder = new StringBuilder();
                for(int j = 0; j < i; ++j) {
                    builder.append(word.charAt(j));
                }
                builder.append('*');
                for(int j = i + 1; j < word.length(); ++j) {
                    builder.append(word.charAt(j));
                }
                String pattern = builder.toString();
                if(!patternToWord.containsKey(pattern)) {
                    patternToWord.put(pattern, new HashSet<>());
                }
                if(!wordToPattern.containsKey(word)) {
                    wordToPattern.put(word, new HashSet<>());
                }
                patternToWord.get(pattern).add(word);
                wordToPattern.get(word).add(pattern);
            }
        }
        Map<String, Set<String>> graph = new HashMap<>();
        for(String word: wordSet) {
            Set<String> edges = wordToPattern
                    .get(word)
                    .stream().
                            flatMap(p -> patternToWord.get(p).stream())
                    .collect(Collectors.toSet());
            edges.remove(word);
            graph.putIfAbsent(word, edges);
        }
        return graph;
    }
}
