import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelsInBinaryTree {
    // v1, BFS
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(root);
        while(!q.isEmpty()) {
            int N = q.size();
            double sum = 0.0;
            for(int i = 0; i < N; i++) {
                TreeNode front = q.poll();
                if(front.left != null) q.add(front.left);
                if(front.right != null) q.add(front.right);
                sum += front.val;
            }
            res.add(sum/N);
        }
        return res;
    }

    // v2, DFS
    public List<Double> averageOfLevels2(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if(root == null) return res;
        List<Integer> count = new ArrayList<>();

        dfs(root, 0, res, count);

        for(int i = 0; i < res.size(); i++) {
            res.set(i, res.get(i)/count.get(i));
        }
        return res;
    }

    // preorder traversal of the tree
    void dfs(TreeNode root, int depth, List<Double> res, List<Integer> count) {
        if(root == null) return;

        if(res.size() == depth) {
            res.add((double) root.val);
            count.add(1);
        } else {
            res.set(depth, res.get(depth)+root.val);
            count.set(depth, count.get(depth)+1);
        }
        dfs(root.left, depth+1, res, count);
        dfs(root.right, depth+1, res, count);
    }
}
