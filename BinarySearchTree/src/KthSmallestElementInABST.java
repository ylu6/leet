import java.util.ArrayDeque;
import java.util.Deque;

public class KthSmallestElementInABST {

    // v1, binary search
    public int kthSmallest(TreeNode root, int k) {
        int count = nodeCount(root.left);
        if(count + 1 == k) return root.val;
        else if (count + 1 > k) return kthSmallest(root.left, k);
        else return kthSmallest(root.right, k-count-1);
    }

    int nodeCount(TreeNode root) {
        if(root == null) return 0;
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    // iterative, inorder traversal
    public int kethSmallest2(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while(k > 0) {
            while(root != null) {
                stack.addFirst(root);
                root = root.left;
            }
            root = stack.pollFirst();
            k--;
            if(k == 0) break;
            root = root.right;
        }
        return root.val;
    }
}
