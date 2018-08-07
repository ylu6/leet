/**
 * Created by yeqing on 7/25/2017.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode curr = head, curr1 = dummy1, curr2 = dummy2;

        while (curr != null) {
            if (curr.val < x) {
                curr1.next = curr;
                curr1 = curr1.next;
            }
            else {
                curr2.next = curr;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        curr1.next = dummy2.next;
        curr2.next = null;
        return dummy1.next;
    }
}
