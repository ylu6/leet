import java.util.ArrayDeque;
import java.util.Deque;

public class InvertBinaryTree {
    // v1, recursive
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;

        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }
    // v2, use stack, DFS
    public TreeNode invertTree2(TreeNode root) {
        if(root == null) return root;
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.addFirst(root);
        while(!stack.isEmpty()) {
            TreeNode top = stack.pollFirst();
            TreeNode left = top.left;
            top.left = top.right;
            top.right = left;
            if(top.left != null) stack.addFirst(top.left);
            if(top.right != null) stack.addFirst(top.right);
        }
        return root;
    }

}
