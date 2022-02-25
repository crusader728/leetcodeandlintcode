package withJava.crusader728.leetcode.divideandconquer;

public class SortList148 {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode mid = getMid(head);
        ListNode sortedLeft = sortList(head);
        ListNode sortedRight = sortList(mid);

        return merge(sortedLeft, sortedRight);
    }



    private  ListNode getMid(ListNode head) {
        ListNode midPrev = null;
        while (head != null && head.next != null) {
            midPrev = (midPrev == null) ? head : midPrev.next;
            head = head.next.next;
        }
        ListNode mid = midPrev.next;
        midPrev.next = null;
        return mid;
    }



    private ListNode merge(ListNode sortedLeft, ListNode sortedRight) {
        ListNode dummy = new ListNode();
        ListNode p1 = sortedLeft;
        ListNode p2 = sortedRight;
        ListNode tail = dummy;
        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                tail.next = p1;
                p1 = p1.next;
                tail = tail.next;
            } else {
                tail.next = p2;
                p2 = p2.next;
                tail = tail.next;
            }
        }

        if(p1 == null) {
            tail.next = p2;
        } else {
            tail.next = p1;
        }

        return dummy.next;
    }



    private static class ListNode {
        int val;
        ListNode next;
    }
}
