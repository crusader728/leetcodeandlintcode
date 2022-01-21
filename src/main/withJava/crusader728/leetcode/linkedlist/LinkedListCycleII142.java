package withJava.crusader728.leetcode.linkedlist;


public class LinkedListCycleII142 {
    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode node = getCycleNode(head);
        if(node == null) {
            return null;
        } else {
            ListNode p1 = head;
            ListNode p2 = node;
            while(p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
    }

    private ListNode getCycleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                return slow;
            }
        }
        return null;
    }



    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
