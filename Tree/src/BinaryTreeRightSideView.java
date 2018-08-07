import java.util.ArrayList;
import java.util.List;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res, 0);

        return res;
    }

    // preorder traversal of the tree
    // for nodes on the same level, the right most node is always visited last
    // keep updating the res list, in the end we will get the right view of the tree
    void preorder(TreeNode root, List<Integer> res, int depth) {
        if(root == null) return;
        if(res.size() == depth) {
            res.add(root.val);
        } else {
            res.set(depth, root.val);
        }
        preorder(root.left, res, depth+1);
        preorder(root.right, res, depth+1);
    }
}
