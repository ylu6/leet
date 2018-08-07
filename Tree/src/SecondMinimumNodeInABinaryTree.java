/**
 * Created by yeqing on 9/7/2017.
 *  where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 *  then this node's value is the smaller value among its two sub-nodes.
 */

public class SecondMinimumNodeInABinaryTree {
    // return -1 if not found
    public int findSecondMinimumValue(TreeNode root) {
        return dfs(root, root.val);
    }

    // return the smallest number in the tree which is larger than num
    int dfs(TreeNode root, int num) {
        if(root.val > num) return root.val; // root is the minimum node in this sub-tree
        if(root.left == null) return -1; // if root==num, and its child is null, return -1

        // root == num, but its child is not null
        int l = dfs(root.left, num);
        int r = dfs(root.right, num);

        if(l == -1) return r;
        if(r == -1) return l;
        return Math.min(l, r);
    }
}
