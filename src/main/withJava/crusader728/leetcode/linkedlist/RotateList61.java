package withJava.crusader728.leetcode.linkedlist;

public class RotateList61 {
    public ListNode rotateRight(ListNode head, int k) {
        int length = getLength(head);
        if(length == 0 || length == 1) {
            return head;
        }
        int modk = k % length;
        if(modk == 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        ListNode slow = head;
        ListNode fast = head;
        for(int i = 0; i < modk; ++i) {
            fast = fast.next;
        }
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        dummy.next = slow.next;
        fast.next = head;
        slow.next = null;
        return dummy.head;
    }

    private int getLength(ListNode head) {
        int l = 0;
        ListNode p = head;
        while(p != null) {
            p = p.next;
            l++;
        }
        return l;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
