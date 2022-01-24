package withJava.crusader728.leetcode.bfs;

import java.util.*;

public class VerticalOrderTraversalOfBinaryTree987 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if(root == null) {
            return Collections.emptyList();
        }

        Queue<Triple<TreeNode, Integer, Integer>> queue = new LinkedList<>();
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        queue.offer(new Triple<>(root, 0, 0));
        while(!queue.isEmpty()) {
            Triple<TreeNode, Integer, Integer> top = queue.poll();
            int r = top.second;
            int c = top.third;
            TreeNode node = top.first;
            map.computeIfAbsent(c, k -> new TreeMap<>()).computeIfAbsent(r, k -> new ArrayList<>()).add(node.val);
            if(node.left != null) {
                queue.offer(new Triple<>(node.left, r + 1, c - 1));
            }
            if(node.right != null) {
                queue.offer(new Triple<>(node.right, r + 1, c + 1));
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        map.forEach((k, m) -> {
            List<Integer> internal = new ArrayList<>();
            m.forEach((r, l) -> {
                Collections.sort(l);
                internal.addAll(l);
            });
            result.add(internal);
        });
        return result;
    }

    private class Triple<F, S, T> {
        private final F first;
        private final S second;
        private final T third;

        Triple(F f, S s, T t) {
            this.first = f;
            this.second = s;
            this.third = t;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }

        public T getThird() {
            return third;
        }
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
