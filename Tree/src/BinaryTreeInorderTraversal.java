import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by yeqing on 8/29/2017.
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (!stack.isEmpty() || cur != null) {
            // or use while cur != null
            if (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            }
            else {
                res.add(stack.peekFirst().val);
                cur = stack.pollFirst().right;
            }
        }

        return res;
    }
}
