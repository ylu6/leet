public class BalancedBinaryTree {
    // a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
    // By definition, the below tree is balanced
    //       1
    //    2     3
    //  4   5  6
    // 7
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    // return -1 if the sub-tree if not balanced
    // return height of the sub-tree if it is balanced
    int dfs(TreeNode root) {
        if(root == null) return 0;
        int l = dfs(root.left);
        int r = dfs(root.right);
        if(l == -1 || r == -1 || Math.abs(l-r) > 1) return -1;
        return 1 + Math.max(l, r);
    }
}
