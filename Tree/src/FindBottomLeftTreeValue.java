import java.util.ArrayDeque;
import java.util.Queue;

public class FindBottomLeftTreeValue {
    // BFS
    public int findBottomLeftValue(TreeNode root) {
        int BL = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()) {
            int N = q.size();
            BL = q.peek().val;
            while(N-- > 0) {
                TreeNode front = q.poll();
                if(front.left != null) q.add(front.left);
                if(front.right != null) q.add(front.right);
            }
        }
        return BL;
    }

    // DFS
    int BL = 0, maxDepth = 0;
    public int findBottomLeftValue2(TreeNode root) {
        dfs(root, 0);
        return BL;
    }
    void dfs(TreeNode root, int depth){
        if(root == null) return;
        if(depth >= maxDepth) {
            BL = root.val;
            maxDepth = depth;
        }
        dfs(root.right, depth+1);
        dfs(root.left, depth+1);
    }
}
