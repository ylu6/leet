/**
 * Created by yeqing on 9/6/2017.
 */
public class TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;

        if (root.val > R)   return trimBST(root.left, L, R);
        else if (root.val < L)  return trimBST(root.right, L, R);
        else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }
}
