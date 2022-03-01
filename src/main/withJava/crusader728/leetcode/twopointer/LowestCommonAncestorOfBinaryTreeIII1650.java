package withJava.crusader728.leetcode.twopointer;

public class LowestCommonAncestorOfBinaryTreeIII1650 {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node p1 = p;
        Node p2 = q;
        while(p1 != p2) {
            p1 = p1.parent;
            p2 = p2.parent;
            if(p1 == null) {
                p1 = q;
            }
            if(p2 == null) {
                p2 = p;
            }
        }
        return p1;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
    }
}
