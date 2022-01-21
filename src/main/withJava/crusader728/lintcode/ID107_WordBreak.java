package withJava.crusader728.lintcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ID107_WordBreak {
    private static class TrieNode {
        private boolean wordHere;
        private Map<Character, TrieNode> children;

        TrieNode() {
            this.wordHere = false;
            this.children = new HashMap<>();
        }

        boolean getWordHere() {
            return wordHere;
        }

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        void setWordHere(boolean b) {
            this.wordHere = b;
        }
    }

    private void insert(String s, int i, TrieNode node) {
        if(i == s.length()) {
            node.setWordHere(true);
        } else {
            Character ch = s.charAt(i);
            Map<Character, TrieNode> children = node.getChildren();
            if(!children.containsKey(ch)) {
                children.put(ch, new TrieNode());
            }
            insert(s, i + 1, children.get(ch));
        }
    }

    public boolean wordBreak(String s, Set<String> wordSet) {
        TrieNode root = new TrieNode();
        root.setWordHere(true);
        for(String word: wordSet) {
            insert(word, 0, root);
        }
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        boolean containsAll = true;
        for(int i = 0; i < 26; ++i) {
            String single = String.valueOf((char)('a' + i));
            if(!wordSet.contains(single)) {
                containsAll = false;
                break;
            }
        }
        if(containsAll) {
            return true;
        } else {
            return helper(s, 0, 0, root, root, memo);
        }
    }

    private boolean helper(String s, int start, int pivot, TrieNode root, TrieNode prev, int[] memo) {


        if(memo[start] != -1) {
            return memo[start] == 1;
        }
        if(pivot == s.length()) {
            memo[start] = prev.wordHere ? 1 : 0;
            return memo[start] == 1;

        }
        if(!prev.getChildren().containsKey(s.charAt(pivot))) {
            memo[start] = 0;
            return memo[start] == 1;
        }
        TrieNode current = prev.getChildren().get(s.charAt(pivot));
        boolean result;
        if(current.wordHere) {
            result = helper(s, pivot + 1, pivot + 1, root, root, memo);
            if(!result) {
                result = helper(s, start, pivot + 1, root, current, memo);
            }
        } else {
            result = helper(s, start, pivot + 1, root, current, memo);
        }
        memo[start] = result? 1 : 0;
        return result;
    }
}
