package withJava.crusader728.leetcode.divideandconquer;

public class MergeKSortedLists23 {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        } else {
            return helper(lists, 0, lists.length);
        }
    }

    private ListNode helper(ListNode[] lists, int from, int to) {
        if(from >= to) {
            return null;
        } else if(from + 1 == to) {
            return lists[from];
        } else {
            int mid = from + (to - from) / 2;
            ListNode left = helper(lists, from, mid);
            ListNode right = helper(lists, mid, to);
            return merge(left, right);
        }
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) {
            return null;
        } else if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        } else {
            ListNode p1 = l1;
            ListNode p2 = l2;
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            while(p1 != null && p2 != null) {
                if(p1.val > p2.val) {
                    tail.next = p2;
                    p2 = p2.next;
                    tail = tail.next;
                    tail.next = null;
                } else {
                    tail.next = p1;
                    p1 = p1.next;
                    tail = tail.next;
                    tail.next = null;
                }
            }
            while(p1 != null) {
                tail.next = p1;
                p1 = p1.next;
                tail = tail.next;
                tail.next = null;
            }
            while(p2 != null) {
                tail.next = p2;
                p2 = p2.next;
                tail = tail.next;
                tail.next = null;
            }
            return dummy.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;
    }
}
