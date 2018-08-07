public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if((l != null && r != null) || (root == p || root == q))
            return root; // one of p and q is in root.left and the other is in root.right, root is LCA
        else if (l != null) return l; // one of p and q is ine root.left, and none in root.right
        else if (r != null) return r;
        else return null;
    }
}
