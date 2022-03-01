package withJava.crusader728.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class CloneNaryTree1490 {
    public Node cloneTree(Node root) {
        if(root == null) {
            return null;
        } else {
            List<Node> result = new ArrayList<>();
            for(Node child: root.children) {
                result.add(cloneTree(child));
            }
            Node ans = new Node();
            ans.val = root.val;
            ans.children = result;
            return ans;
        }
    }

    private static class Node {
        int val;
        List<Node> children;
    }
}
