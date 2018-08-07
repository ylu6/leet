public class LowestCommonAncestorOfABinarySearchTree {
    // if root.val < p.val, candidate must in the right sub-tree: root.right
    // if root.val > q.val, candidate must in the left sub-tree: root.left
    // if root.val >= p.val && root.val <= q.val, root must be the lowest common ancestor, why?
    // if we go lower, to root.left, then q is not in this sub-tree, because there is no duplicate
    // if we go root.right, then p is not in this sub-tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) return lowestCommonAncestor(root, q, p);
        if(root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q); // go left
        } else if (root.val < p.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else{
            return root;
        }
    }

    // v2: iterative approach
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);

        while(true) {
            if(root.val > q.val) root = root.left;
            else if (root.val < p.val) root = root.right;
            else break;
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        LowestCommonAncestorOfABinarySearchTree sol = new LowestCommonAncestorOfABinarySearchTree();
        System.out.println(sol.lowestCommonAncestor(root, new TreeNode(1), new TreeNode(2)).val);
    }
}
