import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
    // level order traversal, use queue
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);

        while(!q.isEmpty()) {
            int n = q.size(), max = q.peek().val;
            while(n-- > 0) {
                TreeNode top = q.poll();
                if(top.left != null) q.add(top.left);
                if(top.right != null) q.add(top.right);
                max = Math.max(max, top.val);
            }
            res.add(max);
        }

        return res;
    }

    // v2, dfs
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    void dfs(TreeNode root, List<Integer> res, int depth) {
        if(root == null) return;
        if(depth == res.size()) res.add(root.val);
        else {
            res.set(depth, Math.max(res.get(depth), root.val));
        }
        dfs(root.left, res, depth+1);
        dfs(root.right, res, depth+1);
    }
}
