package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiameterOfNaryTree1522 {
    public int diameter(Node root) {
        Info result = helper(root);
        if(result == null) {
            return 0;
        } else {
            return result.diameter;
        }
    }


    private Info helper(Node root) {
        if(root == null) {
            return null;
        }
        if(root.children == null || root.children.isEmpty()) {
            Info result = new Info();
            result.diameter = 0;
            result.height = 0;
            return result;
        } 

        int maxDiameter = 0;
        int fst = 0;
        int snd = 0;
        for(Node child: root.children) {
            Info info = helper(child);
            maxDiameter = Math.max(maxDiameter, info.diameter);
            int h = info.height + 1;
            if(h >= snd && h < fst) {
                snd = h;
            } else if(h >= fst) {
                snd = fst;
                fst = h;
            }
        }
        Info result = new Info();
        result.height = fst;
        maxDiameter = snd == 0 ? Math.max(maxDiameter, fst) : Math.max(maxDiameter, fst + snd);
        result.diameter = maxDiameter;
        return result;
    }


    private static class Node {
        int val;
        List<Node> children;

        Node(int v) {
            this.val = v;
            this.children = new ArrayList<>();
        }

        Node(int v, List<Node> nodes) {
            this.val = v;
            this.children = nodes;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", children=" + children +
                    '}';
        }
    }

    private static class Info {
        int diameter;
        int height;
    }

    public static void main(String[] args) {
        DiameterOfNaryTree1522 diameterOfNaryTree1522 = new DiameterOfNaryTree1522();
        diameterOfNaryTree1522.diameter(new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4))));
    }

}
