import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yeqing on 12/8/2017.
 */
public class RecoverBinarySearchTree {
    // v1, recursive approach
    TreeNode prvNode = new TreeNode(Integer.MIN_VALUE), firstNode = null, secondNode = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);

        if (root.val < prvNode.val) {
            if (firstNode==null) firstNode = prvNode;
            secondNode = root;
        }

        inorder(root.right);
        prvNode = root;
    }

    // v2, iterative approach
    public void recoverTree2(TreeNode root) {
        TreeNode prv = null, first = null, second = null;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            if(prv != null && prv.val > root.val) { // find a incorrect pair
                if(first == null) first = prv;
                second = root;
            }
            prv = root;
            root = root.right; // go right
        }

        int temp = second.val;
        second.val = first.val;
        first.val = temp;
    }
}
