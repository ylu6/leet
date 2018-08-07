/**
 * Created by yeqing on 11/16/2017.
 */
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0; // length of the original LinkedList
        ListNode cur = root, prv = null;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        // first section of the parts (numOfParts) each contains numInParts+1 elements
        // remaining parts each contains numInParts elements
        int numInParts = len/k;
        int numOfParts = len%k;
        int idx = 0;
        cur = root;
        while (cur != null) {
            res[idx++] = cur;
            int count = numInParts;
            if (numOfParts > 0) count++;
            while (count > 0) {
                prv = cur;
                cur = cur.next;
            }
            prv.next = null;
            numOfParts--;
        }

        return res;
    }
}
