package withJava.crusader728.leetcode.ood;

import java.util.*;
import java.util.stream.Collectors;

public class DesignSearchAutocompleteSystem642 {
    private static class AutocompleteSystem {
        private static class TrieNode {
            Map<Character, TrieNode> children;
            int count;
            boolean terminate;

            TrieNode() {
                children = new HashMap<>();
            }
        }
        private static class Info {
            String sentence;
            int count;
        }

        private static class Trie {
            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            public void insert(String sentence, int count) {
                TrieNode current = root;
                for(int i = 0; i < sentence.length(); ++i) {
                    char ch = sentence.charAt(i);
                    TrieNode child;
                    if(current.children.containsKey(ch)) {
                        child = current.children.get(ch);
                        child.count += count;
                    } else {
                        child = new TrieNode();
                        child.count = count;
                        current.children.put(ch, child);
                    }
                    current = child;
                }
                current.terminate = true;
            }

            public List<String> topk(TrieNode node, int k) {
                PriorityQueue<Info> priorityQueue = new PriorityQueue<>(infoComparator.reversed());
                StringBuilder builder = new StringBuilder();
                dfs(node, k, builder, priorityQueue);
                List<String> result = new ArrayList<>();
                while(!priorityQueue.isEmpty()) {
                    result.add(0, priorityQueue.poll().sentence);
                }
                return result;
            }

            private void dfs(TrieNode node, int k, StringBuilder builder, PriorityQueue<Info> priorityQueue) {
                if(node.terminate) {
                    if(priorityQueue.isEmpty() || priorityQueue.size() < k) {
                        Info info = new Info();
                        info.sentence = builder.toString();
                        info.count = node.count;
                        priorityQueue.offer(info);
                    } else if(node.count >= priorityQueue.peek().count) {
                        Info info = new Info();
                        info.sentence = builder.toString();
                        info.count = node.count;
                        priorityQueue.offer(info);
                        while(priorityQueue.size() > k) {
                            priorityQueue.poll();
                        }
                    }
                } else {
                    for(Map.Entry<Character, TrieNode> child: node.children.entrySet()) {
                        builder.append(child.getKey());
                        dfs(child.getValue(), k, builder, priorityQueue);
                        builder.deleteCharAt(builder.length() - 1);
                    }
                }
            }



            private static Comparator<String> strComparator = new Comparator<String>() {

                @Override
                public int compare(String o1, String o2) {
                    int i = 0;
                    while(i < o1.length() && i < o2.length()) {
                        if(o1.charAt(i) > o2.charAt(i)) {
                            return 1;
                        } else if(o1.charAt(i) < o2.charAt(i)) {
                            return -1;
                        } else {
                            i++;
                        }
                    }
                    if(o1.length() > o2.length()) {
                        return 1;
                    } else if(o1.length() == o2.length()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            };

            private static Comparator<Info> infoComparator = new Comparator<Info>(){

                @Override
                public int compare(Info o1, Info o2) {
                    int r = Integer.compare(o2.count, o1.count);
                    if(r != 0) {
                        return r;
                    } else {
                        return strComparator.compare(o1.sentence, o2.sentence);
                    }
                }};
        }

        Trie trie = new Trie();
        StringBuilder builder = new StringBuilder();
        TrieNode current = trie.root;

        public AutocompleteSystem(String[] sentences, int[] times) {
            for(int i = 0; i < sentences.length; ++i) {
                trie.insert(sentences[i], times[i]);
            }
        }
        
        public List<String> input(char c) {
            if(c == '#') {
                String setence = builder.toString();
                trie.insert(setence, 1);
                builder = new StringBuilder();
                return new ArrayList<>();
            } else if(current == null || !current.children.containsKey(c)) {
                builder.append(c);
                return new ArrayList<>();
            } else {
                builder.append(c);
                current = current.children.get(c);
                String prefix = builder.toString();
                return trie
                    .topk(current, 3)
                    .stream()
                    .map(s -> prefix + s)
                    .collect(Collectors.toList());
            }
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(new String[] {
                "i love you", "island", "ironman", "i love leetcode"
        }, new int[] {5, 3, 2, 2});
        System.out.println(autocompleteSystem.input('i'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));
    }
}
