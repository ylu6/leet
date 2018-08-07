public class BinaryTreeTilt {
    int tilt = 0;
    public int findTilt(TreeNode root) {
        postorder(root);
        return tilt;
    }
    // return sum of subtree
    int postorder(TreeNode root) {
        if(root == null) return 0;
        int l = postorder(root.left);
        int r = postorder(root.right);
        tilt += Math.abs(l-r); // update tilt
        return l + r + root.val;
    }
}
