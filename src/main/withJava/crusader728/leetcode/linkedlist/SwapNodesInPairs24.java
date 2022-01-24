package withJava.crusader728.leetcode.linkedlist;

public class SwapNodesInPairs24 {
    private class ListNode {
        int val;
        ListNode next;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        ListNode first = dummy.next;
        while(first != null && first.next != null) {
            ListNode second = first.next;
            ListNode next = second.next;
            prev.next = second;
            second.next = first;
            first.next = next;
            prev = first;
            first = next;
        }

        return dummy.next;
    }


}
