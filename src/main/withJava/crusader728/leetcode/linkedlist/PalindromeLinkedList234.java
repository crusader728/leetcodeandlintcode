package withJava.crusader728.leetcode.linkedlist;

public class PalindromeLinkedList234 {
    public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }

        ListNode firstHalfEnd = getFirstHalfEnd(head);
        ListNode reversedSecondHalf = reverse(firstHalfEnd.next);
        ListNode p1 = head;
        ListNode p2 = reversedSecondHalf;
        while(p1 != null && p2 != null && p1.val == p2.val) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2 == null;
    }

    private ListNode reverse(ListNode next) {
        ListNode result = null;
        ListNode p = next;
        while(p != null) {
            ListNode temp = p.next;
            p.next = result;
            result = p;
            p = temp;
        }
        return result;
    }

    private ListNode getFirstHalfEnd(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
