package withJava.crusader728.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ID683_WordBreakIII {
    private static class TrieNode {
        private boolean wordHere;
        private Map<Character, TrieNode> children;

        TrieNode() {
            this.wordHere = false;
            this.children = new HashMap<>();
        }
    }

    private void insert(String s, int index, TrieNode node) {
        if(index == s.length()) {
            node.wordHere = true;
        } else {
            char ch = s.charAt(index);
            if(!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            insert(s, index + 1, node.children.get(ch));
        }
    }

    public int wordBreak3(String s, Set<String> dict) {
        if(s == null || dict == null || s.length() == 0 || dict.isEmpty()) {
            return 0;
        }

        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        TrieNode root = new TrieNode();
        for(String word: dict) {
            insert(word.toLowerCase(), 0, root);
        }

        return helper(s.toLowerCase(), root, 0, 0, root, memo);
    }

    private int helper(String s, TrieNode root, int start, int pivot, TrieNode prev, int[] memo) {

        if(memo[start] != -1) {
            return memo[start];
        }
        if(start == s.length()) {
            return 0;
        }
        if(pivot == s.length()) {
            if(prev.wordHere) {
                memo[start] = 1;
            } else {
                memo[start] = 0;
            }
            return memo[start];
        }
        char ch = s.charAt(pivot);
        if(!prev.children.containsKey(ch)) {
            memo[start] = 0;
            return 0;
        }
        TrieNode current = prev.children.get(ch);
        int result = 0;
        if(current.wordHere) {
            result += helper(s, root, pivot + 1, pivot + 1, root, memo);
        }
        result += helper(s, root, start, pivot + 1, current, memo);
        memo[start] = result;
        return result;
    }
}
