/**
 * Created by yeqing on 7/25/2017.
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode slow = head, fast = head, prv = null;
        while (fast != null && fast.next != null) {
            prv = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prv.next = null;
        ListNode right = reverseList(slow);
        System.out.println(ListNode.toString(right));
        zip(head, right);
    }

    ListNode reverseList(ListNode head) {
        ListNode prv = null, cur = head, nxt = null;
        while (cur != null) {
            nxt = cur.next;
            cur.next = prv;
            prv = cur;
            cur = nxt;
        }
        return prv;
    }
    void zip(ListNode head1, ListNode head2) {
        ListNode cur1 = head1, cur2 = head2;
        while (cur1 != null) {
            ListNode nxt = cur1.next;
            cur1.next = cur2;
            cur2 = cur2.next;
            if (nxt != null) cur1.next.next = nxt;
            cur1 = nxt;
        }
    }

    public static void main(String[] args) {
        ReorderList sol = new ReorderList();
        int[] nums = {1,2,3,4,5};
        ListNode head = ListNode.arrayToList(nums);
//        System.out.println(ListNode.toString(sol.reverseList(head)));
        sol.reorderList(head);
        System.out.println(ListNode.toString(head));
    }
}
