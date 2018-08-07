/**
 * Created by yeqing on 7/20/2017.
 * Reverse a linked list from position m to n.
 * Do it in-place and in one-pass.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0), cur = dummy, nxt = null;
        dummy.next = head;
        for (int i = 1; i < m; i++) cur = cur.next;
        ListNode prvTail = cur, curTail = cur.next;

        cur = curTail;
        nxt = cur.next;
        for (int i = m; i < n; i++) {
            ListNode tmp = nxt.next;
            nxt.next = cur;
            cur = nxt;
            nxt = tmp;
        }
        prvTail.next = cur;
        curTail.next = nxt;
        return dummy.next;
    }

}
