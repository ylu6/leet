import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumAbsoluteDifferenceInBST {
    // v2, iterative approach
    public int minDiffInBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode prv = null;
        int res = Integer.MAX_VALUE;

        while(!stack.isEmpty() || root != null) {
            while(root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if(prv != null) res = Math.min(res, root.val - prv.val);
            prv = root;
            root = root.right;
        }
        return res;
    }

    // v1, recursive approach, use class variable
    int min = Integer.MAX_VALUE;
    TreeNode prv;
    public int minDiffInBST(TreeNode root) {
        if(root == null) return min;
        minDiffInBST(root.left);
        if(prv != null) min = Math.min(min, root.val - prv.val);
        prv = root;
        minDiffInBST(root.right);
        return min;
    }
}
