/**
 * Created by yeqing on 7/24/2017.
 */
public class SortList {
    public ListNode sortListTopDown(ListNode head) {
        if (head == null || head.next == null) return head; // base case
        int len = 0;
        ListNode curr = head, mid;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        curr = head;
        for (int i = 1; i < len/2; i++) { curr = curr.next; }

        mid = curr.next;
        curr.next = null;
        return merge(sortListTopDown(head), sortListTopDown(mid));
    }
    // top down v2, different way to split the list by two pointer
    public ListNode sortListTopDown2(ListNode head) {
        if (head == null || head.next == null) return head; // base case
        ListNode prv = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prv = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prv.next = null;

        return merge(sortListTopDown2(head), sortListTopDown2(slow));
    }
    // both list1 and list2 are not null
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy, curr1 = list1, curr2 = list2;
        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                curr.next = curr1;
                curr1 = curr1.next;
            }
            else {
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        if (curr1 == null)  curr.next = curr2;
        else                curr.next = curr1;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 3, 2, 3, 4, 9};
        ListNode head = ListNode.arrayToList(nums);
        SortList sol = new SortList();
        ListNode sorted = sol.sortListTopDown2(head);
        System.out.println(ListNode.toString(sorted));
    }
}
