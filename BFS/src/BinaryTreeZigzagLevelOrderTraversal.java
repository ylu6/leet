import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        boolean leftToRight = true;

        while(!q.isEmpty()) {
            List<Integer> row = new ArrayList<>();
            for(int i = 0, sz = q.size(); i < sz; i++) {
                TreeNode cur = q.poll();
                row.add(cur.val);
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
            if(!leftToRight) Collections.reverse(row);
            res.add(row);
            leftToRight = !leftToRight;
        }
        return res;
    }
    class TreeNode{
        int val;
        TreeNode left, right;
    }
}
