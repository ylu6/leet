/**
 * Created by yeqing on 7/25/2017.
 */
public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);
        ListNode cur = head, cur1 = dummy1, cur2 = dummy2;
        int count = 1;
        while (cur != null) {
            if (count%2 == 1) {
                cur1.next = cur;
                cur1 = cur1.next;
            } else {
                cur2.next = cur;
                cur2 = cur2.next;
            }
            cur = cur.next;
            count++;
        }
        cur1.next = dummy2.next;
        cur2.next = null; //avoid cycle in the final list
        return dummy1.next;
    }
}
