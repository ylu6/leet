import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by yeqing on 8/29/2017.
 */
public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

//        while (!stack.isEmpty() || cur != null) {
//            if(cur != null) {
//                res.add(cur.val);
//                stack.addFirst(cur);
//                cur = cur.left;
//            }
//            else {
//                cur = stack.pollFirst().right;
//            }
//        }

        while(cur != null) {
            res.add(cur.val);
            if(cur.right != null) stack.addFirst(cur.right);
            if(cur.left != null) cur = cur.left;
            else cur = stack.pollFirst();
        }

        return res;
    }
}
