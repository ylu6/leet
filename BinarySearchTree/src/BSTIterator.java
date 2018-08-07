import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 8/27/2017.
 */



public class BSTIterator {
    private Deque<TreeNode> stack;
    private boolean reverseOrder;

    public BSTIterator(TreeNode root) {
        this(root, false);
    }

    public BSTIterator(TreeNode root, boolean reverseOrder) {
        stack = new ArrayDeque<>();
        this.reverseOrder = reverseOrder;
        TreeNode node = root;
        while (node != null) {
            stack.addFirst(node);
            node = reverseOrder ? node.right : node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pollFirst();
        int res = node.val;
        node = reverseOrder ? node.left : node.right;
        while (node != null) {
            stack.addFirst(node);
            node = reverseOrder ? node.right : node.left;
        }
        return res;
    }
}
