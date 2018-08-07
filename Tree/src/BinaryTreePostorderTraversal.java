import java.util.*;

/**
 * Created by yeqing on 8/29/2017.
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prv = null;

        while(!stack.isEmpty() || cur != null) {
            while(cur != null) { // search left leaf, till null reached
                stack.addFirst(cur);
                cur = cur.left;
            }
            cur = stack.pollFirst();
            // right is null or just back from right sub-tree
            if (cur.right == null || cur.right == prv) {
                res.add(cur.val);
                prv = cur;
                cur = null; // set cur to null, back to upper TreeNode in next round
            }
            else { // right is not null, and just back from left subtree, continue explore the right sub-tree
                stack.push(cur);
                prv = cur;
                cur = cur.right;
            }
        }

        return res;
    }

    // v2, do root->right->left, and reverse the process by store results in LinkedList, this is equivalent to postorder
    public List<Integer> postorderTraversal2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        LinkedList<Integer> res = new LinkedList<>();

        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                res.addFirst(cur.val);
                stack.addFirst(cur);
                cur = cur.right;
            } else {
                cur = stack.pollFirst().left;
            }
        }

        return res;
    }
}
