public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null, cur = root;

        while(cur != null) {
            if(cur.val > p.val) { // current node value is too big, go left
                res = cur;
                cur = cur.left;
            } else { // go right, if equal or cur.val < p.val, go right
                cur = cur.right; // don't update res when go right
            }
        }
        return res;
    }
}
