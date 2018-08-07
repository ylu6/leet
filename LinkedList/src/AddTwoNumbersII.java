import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 7/25/2017.
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        for (ListNode cur = l1; cur != null; cur=cur.next)
            stack1.addFirst(cur.val);
        for (ListNode cur = l2; cur != null; cur=cur.next)
            stack2.addFirst(cur.val);

        ListNode res = null, tmp = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int n1 = stack1.isEmpty() ? 0 : stack1.pollFirst();
            int n2 = stack2.isEmpty() ? 0 : stack2.pollFirst();
            int sum = n1 + n2 + carry;
            carry = sum > 9 ? 1 : 0;
            tmp = res;
            res = new ListNode(sum%10);
            res.next = tmp;
        }
        return res;
    }
}
