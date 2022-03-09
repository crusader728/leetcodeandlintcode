package withJava.crusader728.leetcode.twopointer;

public class RemoveDuplicatesFromSortedListII82 {
    public ListNode deleteDuplicates(ListNode head) {
            ListNode guard = new ListNode();
            guard.next = head;
            ListNode p1 = head;
            ListNode prev = guard;
            while(p1 != null) {
                ListNode p2 = p1.next;
                while(p2 != null && p2.val == p1.val) {
                    p2 = p2.next;
                }
                if(p2 == p1.next) {
                    prev = p1;
                    p1 = p2;
                } else {
                    prev.next = p2;
                    p1 = p2;
                }
            }
            return guard.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
