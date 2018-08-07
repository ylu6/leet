import javax.swing.tree.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {
    class TreeNode{
        int val;
        TreeNode left, right;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        TreeNode cur;
        while(!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for(int i = 0, len = q.size(); i < len; i++) {
                cur = q.poll();
                list.add(cur.val);
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
            res.add(list);
        }
        return res;
    }
}
