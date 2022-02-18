package withJava.crusader728.leetcode.linkedlist;

public class ReverseLinkedListII92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = null, l = dummy, r = dummy, next = dummy.next;
        for(int i = 0; i < left; ++i) {
            prev = l;
            l = l.next;            
        }
        for(int i = 0; i < right; ++i) {
            r = next;
            next = next.next;
        }

        reverse(l, next);

        prev.next = r;
        l.next = next;
        return dummy.next;

    }

    private void reverse(ListNode l, ListNode next) {
        ListNode dummy = new ListNode();
        ListNode p = l;
        while(p != next) {
            ListNode temp = p.next;
            p.next = dummy.next;
            dummy.next = p;
            p = temp;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
