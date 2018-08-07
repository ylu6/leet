import java.util.ArrayDeque;
import java.util.Deque;

public class ValidateBinarySearchTree {

    // v1: recursive solution
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean isValidBST(TreeNode root, Long min, Long max) {
        if(root.val >= max || root.val <= min) return false;
        return isValidBST(root.left, min, (long) root.val)
                && isValidBST(root.right, (long) root.val, max);
    }

    // v2: iterative solution
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prv = null, cur = root;

        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            if(prv != null && prv.val >= cur.val) return false;
            prv = cur;
            cur = cur.right;
        }
        return false;
    }
}
