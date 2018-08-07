public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode p, TreeNode q) {
        if(p==null && q==null) return true; // both are null
        if(p==null || q==null) return false; // one of them is null
        if(p.val != q.val) return false; // neither of them is null
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);

        SymmetricTree sol = new SymmetricTree();
        System.out.println(sol.isMirror(root.left, root.right));
    }
}
