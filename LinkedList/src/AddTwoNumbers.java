/**
 * Created by yeqing on 7/17/2017.
 */
public class AddTwoNumbers {
    class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr1  = l1, curr2 = l2, curr = dummy;
        int sum = 0, carrier = 0;
        while (curr1 != null | curr2 != null) {
            sum = (curr1==null?0:curr1.val) + (curr2==null?0:curr2.val) + carrier;
            carrier = sum > 9 ? 1 : 0;
            curr.next = new ListNode(sum % 9);
            curr = curr.next;
            if (curr1 != null) curr1 = curr1.next;
            if (curr2 != null) curr2 = curr2.next;
        }
        return dummy.next;
    }
}
