public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(key < root.val) root.left = deleteNode(root.left, key); // go left
        else if (key > root.val) root.right = deleteNode(root.right, key); // go right
        else { // key found, delete it
            if(root.right == null) return root.left; //use left if right is null
            else if(root.left == null) return root.right; // use right if left is null
            else { // use min of right if both left and right are not null
                TreeNode x = root.right;
                while(x.left != null) x = x.left; // in the end, x is the min node in root.right
                root.val = x.val;
                root.right = deleteNode(root.right, x.val);
            }
        }
        return root;
    }
}
