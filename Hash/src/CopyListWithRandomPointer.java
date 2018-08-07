import java.util.HashMap;
import java.util.Map;

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

public class CopyListWithRandomPointer {
    // v1, use HashMap<RandomListNode, RandomListNode>, two pass
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode p1 = head, p2 = dummy, copy;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        while(p1 != null) {
            copy = new RandomListNode(p1.label);
            p2.next = copy;
            map.put(p1, copy);
            p1 = p1.next;
            p2 = p2.next;
        }

        p1 = head;
        p2 = dummy.next;
        while(p1 != null) {
            p2.random = map.get(p1.random);
            p1 = p1.next;
            p2 = p2.next;
        }

        return dummy.next;
    }

    // first insert copied nodes into original list
    // connect the random nodes
    // extract copied nodes and recover original list
    public RandomListNode copyRandomList2(RandomListNode head) {
        if (head == null) return null;
        RandomListNode cur = head, nxt;

        // copy
        while(cur != null) {
            nxt = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = nxt;
            cur = nxt;
        }
        // connect random nodes
        cur = head;
        while(cur != null) {
            nxt = cur.next;
            if(cur.random != null)
                nxt.random = cur.random.next;
            cur = nxt.next;
        }

        // disconnect the nodes
        RandomListNode copy = head.next;
        RandomListNode prv1 = head, prv2 = copy, cur1 = head.next, cur2 = copy.next;
        while(prv2 != null && prv2.next != null) {
            cur1 = prv2.next;
            cur2 = cur1.next;
            prv1.next = cur1;
            prv2.next = cur2;
            prv1 = cur1;
            prv2 = cur2;
        }
        return copy;
    }

    public void printList(RandomListNode root) {
        while(root != null) {
            System.out.print(root.label + " ");
            root = root.next;
        }
    }

    public static void main(String[] args) {
        RandomListNode root = new RandomListNode(1);
        root.next = new RandomListNode(2);
        root.next.next = new RandomListNode(3);
        CopyListWithRandomPointer sol = new CopyListWithRandomPointer();
        sol.copyRandomList2(root);
    }
}
