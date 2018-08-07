/**
 * Created by yeqing on 7/25/2017.
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prv = dummy, curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val != curr.next.val) {
                prv = curr;
                curr = curr.next;
                continue;
            }
            while (curr.next != null && curr.val == curr.next.val)
                curr = curr.next;
            prv.next = curr.next;
            curr = prv.next;
        }
        return dummy.next;
    }
}
