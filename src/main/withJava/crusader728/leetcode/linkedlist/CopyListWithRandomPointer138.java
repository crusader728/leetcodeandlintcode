package withJava.crusader728.leetcode.linkedlist;

public class CopyListWithRandomPointer138 {
    public Node copyRandomList(Node head) {
        if(head == null) {
            return null;
        }

        Node p = head;
        while(p != null) {
            Node cloned = new Node(p.val);
            cloned.next = p.next;
            p.next = cloned;
            p = cloned.next;
        }

        p = head;
        while(p != null) {
            Node cloned = p.next;
            cloned.random = p.random == null ? null : p.random.next;
            p = cloned.next;
        }

        p = head;
        Node clonedHead = p.next;
        Node q = p.next;
        while(p != null) {
            p.next = q.next;
            q.next = p.next == null ? null : p.next.next;
            p = p.next;
            q = q.next;
        }
        return clonedHead;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        Node(int v) {
            this.val = v;
        }
    }

}
