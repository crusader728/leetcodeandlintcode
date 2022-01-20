package java.crusader728.leetcode.linkedlist;

public class AddTwoNumbers2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode dummy = new ListNode();
        ListNode p = dummy;
        while(p1 != null && p2 != null) {
            int value = p1.val + p2.val + (carry ? 1 : 0);
            carry = value >= 10;
            p.next = new ListNode(value % 10);
            p = p.next;
            p1 = p1.next;
            p2 = p2.next;
        }

        if(p1 != null) {
            while(p1 != null) {
                int value = p1.val + (carry ? 1 : 0);
                carry = value >= 10;
                p.next = new ListNode(value % 10);
                p = p.next;
                p1 = p1.next;
            }
        } else {
            while(p2 != null) {
                int value = p2.val + (carry ? 1 : 0);
                carry = value >= 10;
                p.next = new ListNode(value % 10);
                p = p.next;
                p2 = p2.next;
            }
        }
        if(carry) {
            p.next = new ListNode(1);
        }
        return dummy.next;
    }
}
