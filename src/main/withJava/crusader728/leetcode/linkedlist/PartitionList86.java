package withJava.crusader728.leetcode.linkedlist;

public class PartitionList86 {
    public ListNode partition(ListNode head, int x) {
        if(head == null) {
            return null;
        }
        ListNode dummySmall = new ListNode();
        ListNode dummyLarge = new ListNode();
        ListNode smallEnd = dummySmall;
        ListNode LargeEnd = dummyLarge;
        ListNode p = head;
        while(p != null) {
            if(p.val < x) {
                smallEnd.next = p;
                p = p.next;
                smallEnd = smallEnd.next;
                smallEnd.next = null;
            } else {
                LargeEnd.next = p;
                p = p.next;
                LargeEnd = LargeEnd.next;
                LargeEnd.next = null;
            }
        }
        smallEnd.next = dummyLarge.next;
        return dummySmall.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
