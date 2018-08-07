/**
 * Created by yeqing on 7/20/2017.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    public static ListNode arrayToList(int[] nums) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int n : nums){
            cur.next = new ListNode(n);
            cur = cur.next;
        }
        return dummy.next;
    }
    public static String toString(ListNode head) {
        ListNode curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val).append(" ");
            curr = curr.next;
        }
        return sb.toString();
    }
}
public class ReverseLinkedList {
    // recursive solution
    public ListNode reverseList(ListNode head) {
        if (head.next == null) return head;
        ListNode new_head = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return new_head;
    }
    // iterative solution
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = newHead;
            newHead = curr;
            curr = next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ReverseLinkedList sol = new ReverseLinkedList();
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
        int[] nums = {1,2,3};
        ListNode head = ListNode.arrayToList(nums);
        System.out.println(ListNode.toString(head));
        ListNode new_head = sol.reverseList2(head);
        System.out.println(ListNode.toString(new_head));
    }
}
