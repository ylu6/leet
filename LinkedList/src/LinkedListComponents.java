import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LinkedListComponents {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    // e.g. 0->1->2->3->4->5->6, G: {0,1,2, 4, 5}
    // we iterate through the list and increase count by 1 at node 2 and 5
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> gSet = new HashSet<Integer>();
        for(int n : G) gSet.add(n);

        int count = 0;
        while(head != null) {
            if(gSet.contains(head.val) && (head.next == null || !gSet.contains(head.next.val)))
                count++;
            head = head.next;
        }
        return count;
    }
}
