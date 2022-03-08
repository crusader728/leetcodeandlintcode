package withJava.crusader728.leetcode.linkedlist;

public class LinkedListCycle141 {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
