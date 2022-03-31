package withJava.crusader728.leetcode.twopointer;

public class InsertIntoSortedCircularLinkedList708 {
    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        Node current = head.next;
        Node prev = head;
        while(prev.next != head) {
            if(insertVal >= prev.val && insertVal <= current.val) {
                break;
            } else if(prev.val > current.val && (insertVal > prev.val || insertVal < current.val)) {
                break;
            }
            prev = prev.next;
            current = current.next;
        }

        Node node = new Node(insertVal);
        node.next = current;
        prev.next = node;
        return head;
        
    }

    private static class Node {
        int val;
        Node next;

        Node(int v) {
            this.val = v;
        }
    }
}
