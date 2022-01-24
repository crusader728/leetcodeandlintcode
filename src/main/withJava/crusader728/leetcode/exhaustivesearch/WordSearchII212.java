package withJava.crusader728.leetcode.exhaustivesearch;


import java.util.*;

public class WordSearchII212 {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        TrieNode root = new TrieNode();
        for(String word: words) {
            insert(word, root);
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[i].length; ++j) {
                search(board, i, j, root, visited, result);
            }
        }
        return new ArrayList<>(result);
    }

    private void search(char[][] board, int i, int j, TrieNode root, boolean[][] visited, Set<String> result) {
        char ch = board[i][j];
        if(root.children.containsKey(ch)) {
            TrieNode trieNode = root.getChildren().get(ch);
            if(trieNode.word != null) {
                result.add(trieNode.word);
            }
            visited[i][j] = true;
            for(int k = 0; k < dx.length; ++k) {
                int r = i + dx[k];
                int c = j + dy[k];
                if(r >= 0 && r < board.length && c >= 0 && c < board[r].length && !visited[r][c]) {
                    search(board, r, c, trieNode, visited, result);
                }
            }
            visited[i][j] = false;
        }
    }

    private int[] dx = new int[] {0, 0, 1, -1};
    private int[] dy = new int[] {-1, 1, 0, 0};

    private static class TrieNode {
        private String word;
        private Map<Character, TrieNode> children = new HashMap<>();

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }

        public void setChildren(Map<Character, TrieNode> children) {
            this.children = children;
        }
    }

    private void insert(String word, TrieNode root) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)) {
                TrieNode child = new TrieNode();
                node.getChildren().put(ch, child);
            }
            node = node.getChildren().get(ch);
        }
        node.word = word;
    }




}
