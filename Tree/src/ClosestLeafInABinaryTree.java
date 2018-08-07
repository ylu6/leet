import java.util.*;

/**
 * Created by yeqing on 12/12/2017.
 */
public class ClosestLeafInABinaryTree {

    public int findClosestLeaf(TreeNode root, int k) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        Map<TreeNode, List<TreeNode>> adj = new HashMap<>();
        dfs(root, adj, queue, k);
        Set<TreeNode> visited = new HashSet<>();

        for (TreeNode node : adj.keySet()) {
            System.out.print(node.val + ": ");
            for (TreeNode n : adj.get(node))
                System.out.print(n.val + " ");
            System.out.println();
        }
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if(visited.contains(cur)) continue;
            visited.add(cur);
            if(cur.left == null && cur.right == null) return cur.val;
            for(TreeNode node : adj.get(cur))
                queue.add(node);
        }
        return -1; // if not exist
    }
    // convert a tree to a non-directed graph, store as adjacent list
    private void dfs(TreeNode root, Map<TreeNode, List<TreeNode>> adj, Queue<TreeNode> q, int k) {
        adj.putIfAbsent(root, new ArrayList<TreeNode>());
        if(root.val == k) q.add(root);
        if(root.left != null) {
            adj.get(root).add(root.left);
            adj.put(root.left, new ArrayList<TreeNode>(Arrays.asList(root)));
            dfs(root.left, adj, q, k);
        }
        if(root.right != null) {
            adj.get(root).add(root.right);
            adj.put(root.right, new ArrayList<TreeNode>(Arrays.asList(root)));
            dfs(root.right, adj, q, k);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.left.left.left = new TreeNode(6);
        ClosestLeafInABinaryTree sol = new ClosestLeafInABinaryTree();
        sol.findClosestLeaf(root, 2);
    }
}
