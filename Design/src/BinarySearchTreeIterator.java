import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator {

    class TreeNode {
        int val;
        TreeNode left, right;
    }

    Deque<TreeNode> stack;
    TreeNode cur;

    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        cur = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }

    /** @return the next smallest number */
    public int next() {
        while(cur != null) {
            stack.addFirst(cur);
            cur = cur.left;
        }
        cur = stack.pollFirst();
        int res = cur.val;
        cur = cur.right;
        return res;
    }
}
