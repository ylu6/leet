/**
 * Created by yeqing on 7/26/2017.
 */
public class ReverseNodesInkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;
        ListNode dummy = new ListNode(0), tail = dummy;
        dummy.next = head;

        ListNode cur = head, nxt, newTail;

        while (cur != null) {
            ListNode newHead = cur, prv = null;
            //search for next newHead
            for (int i = 1; i < k && newHead != null; i++) {
                newHead = newHead.next;
            }
            // reached end of List, size of current SubList is smaller than k, break
            if (newHead == null) {
                tail.next = cur;
                break;
            }
            // reverse SubList
            newTail = cur;
            while (true) {
                nxt = cur.next;
                cur.next = prv;
                prv = cur;
                if (cur == newHead) break; // stop reversing if newHead reached
                cur = nxt;
            }
            cur = nxt; // cur points to first node of next SubList
            tail.next = newHead; // connect previous tail with head of current SubList
            tail = newTail; // tail points to the tail node of current SubList
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums= {1,2,3,4,5};
        ListNode head = ListNode.arrayToList(nums);
        ReverseNodesInkGroup sol = new ReverseNodesInkGroup();
        head = sol.reverseKGroup(head,2);
        System.out.println(ListNode.toString(head));
    }
}
