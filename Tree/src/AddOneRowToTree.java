public class AddOneRowToTree {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if(d==1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        dfs(root, v, d-2);
        return root;
    }

    void dfs(TreeNode root, int v, int count) {
        if(root == null) return;
        TreeNode temp;
        if(count == 0) {
            temp = root.left;
            root.left = new TreeNode(v);
            root.left.left = temp;
            temp = root.right;
            root.right = new TreeNode(v);
            root.right.right = temp;
        }
        dfs(root.left, v, count-1);
        dfs(root.right, v, count-1);
    }
}
