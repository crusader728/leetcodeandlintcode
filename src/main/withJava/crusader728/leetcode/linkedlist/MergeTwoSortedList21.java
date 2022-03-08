package withJava.crusader728.leetcode.linkedlist;

public class MergeTwoSortedList21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode cur = dummy;
        while(p1 != null && p2 != null) {
            if(p1.val <= p2.val) {
                cur.next = p1;
                p1 = p1.next;
                cur = cur.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
                cur = cur.next;
            }
        }

        if(p1 != null) {
            cur.next = p1;
        } else {
            cur.next = p2;
        }
        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
    }
}
